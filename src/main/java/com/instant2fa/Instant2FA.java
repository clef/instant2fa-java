package com.instant2fa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Instant2FA {
    private final static String DEFAULT_BASE_URL = "https://api.instant2fa.com/";
    private ObjectMapper mapper;
    private Instant2FAService api;

    public Instant2FA(String baseURL, String accessKey, String accessSecret) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.mapper = mapper;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new BasicAuthInterceptor(accessKey, accessSecret));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(new JSONAPIConverterFactory(
                        mapper,
                        UserAccessToken.class,
                        VerificationRequest.class,
                        VerificationResponseToken.class,
                        VerificationResponse.class))
                .client(httpClient.build())
                .build();
        this.api = retrofit.create(Instant2FAService.class);
    }

    public Instant2FA(String accessKey, String accessSecret) {
        this(DEFAULT_BASE_URL, accessKey, accessSecret);
    }

    public String createSettings(String distinctID) throws IOException, APIException {
        UserAccessToken token = new UserAccessToken();
        token.distinctID = distinctID;

        Call<JSONAPIDocument<UserAccessToken>> call = this.api.createUserAccessToken(new JSONAPIDocument<UserAccessToken>(token));
        Response<JSONAPIDocument<UserAccessToken>> response = call.execute();
        if (!response.isSuccessful()) {
            throw new APIException(parseError(response).errors);
        }
        return response.body().get().hostedPageURL;
    }

    public String createVerification(String distinctID) throws IOException, MFANotEnabledException, APIException {
        VerificationRequest request = new VerificationRequest();
        request.distinctID = distinctID;

        Call<JSONAPIDocument<VerificationRequest>> call = this.api.createVerificationRequest(new JSONAPIDocument<VerificationRequest>(request));
        Response<JSONAPIDocument<VerificationRequest>> response = call.execute();
        if (response.code() == 422) {
            throw new MFANotEnabledException();
        } else if (!response.isSuccessful()) {
            throw new APIException(parseError(response).errors);
        }
        return response.body().get().hostedPageURL;
    }

    public boolean confirmVerification(String distinctID, String tokenString) throws IOException, VerificationMismatchException, VerificationFailedException, APIException {
        VerificationResponseToken token = new VerificationResponseToken(tokenString);

        Call<JSONAPIDocument<VerificationResponse>> call = this.api.getVerificationResponse(token.id);
        Response<JSONAPIDocument<VerificationResponse>> httpResponse = call.execute();

        if (!httpResponse.isSuccessful()) {
            throw new APIException(parseError(httpResponse).errors);
        }

        VerificationResponse response = httpResponse.body().get();
        if (!Objects.equals(response.distinctID, distinctID)) {
            throw new VerificationMismatchException("The distinctID passed back from the request " +
                    "didn't match the one passed into this function. Are you passing in the right " +
                    "value for distinctID?");
        }
        else if (!response.isSuccessful()) {
            throw new VerificationFailedException(response.status);
        }
        return response.isSuccessful();
    }

    private ErrorDocument parseError(Response response) {
        try {
            ErrorDocument error = this.mapper.readValue(response.errorBody().string(), ErrorDocument.class);
            return error;
        } catch (IOException e) {
            return new ErrorDocument(new APIError[]{
                    new APIError("Unknown error", "", response.code())
            });
        }
    }
}
