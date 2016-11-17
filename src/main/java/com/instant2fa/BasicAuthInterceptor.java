package com.instant2fa;

import java.io.IOException;
import java.util.Base64;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mark on 11/15/16.
 */
class BasicAuthInterceptor implements Interceptor {
    private final String accessSecret;
    private final String accessKey;

    public BasicAuthInterceptor(String accessKey, String accessSecret) {
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
    }

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", this.getHeader())
                .build();

        Response response = chain.proceed(request);
        return response;
    }

    private String getHeader() {
        String credentials = this.accessKey + ":" + this.accessSecret;
        final String basic = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        return basic;
    }
}
