package com.instant2fa;

import com.github.jasminb.jsonapi.JSONAPIDocument;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mark on 11/15/16.
 */
interface Instant2FAService {
    @POST("user-access-tokens/")
    Call<JSONAPIDocument<UserAccessToken>> createUserAccessToken(@Body JSONAPIDocument<UserAccessToken> userAccessToken);

    @POST("verification-requests/")
    Call<JSONAPIDocument<VerificationRequest>> createVerificationRequest(@Body JSONAPIDocument<VerificationRequest> verificationRequest);

    @GET("verification-response-tokens/{id}")
    Call<JSONAPIDocument<VerificationResponse>> getVerificationResponse(@Path("id") String id);
}

