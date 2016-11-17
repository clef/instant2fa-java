package com.instant2fa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorDocument {
    @JsonProperty("errors")
    protected APIError[] errors;

    public ErrorDocument() { }

    public ErrorDocument(APIError[] errors) {
        this.errors = errors;
    }
}
