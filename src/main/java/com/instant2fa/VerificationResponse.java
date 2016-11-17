package com.instant2fa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("verification-responses")
class VerificationResponse {
    @Id
    String id;

    @JsonProperty("distinct_id")
    String distinctID;

    @JsonProperty("status")
    String status;

    public boolean isSuccessful() {
        return Objects.equals(this.status, "succeeded");
    }
}
