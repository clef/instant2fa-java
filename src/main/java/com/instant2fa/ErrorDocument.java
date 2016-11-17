package com.instant2fa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mark on 11/16/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorDocument {
    @JsonProperty("errors")
    protected APIError[] errors;

    public ErrorDocument() { }

    public ErrorDocument(APIError[] errors) {
        this.errors = errors;
    }
}
