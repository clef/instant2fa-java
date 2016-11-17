package com.instant2fa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("verification-response-tokens")
public class VerificationResponseToken {
    @Id
    String id;

    @JsonProperty("distinct_id")
    String distinctID;

    public VerificationResponseToken(String token) {
        this.id = token;
    }
}
