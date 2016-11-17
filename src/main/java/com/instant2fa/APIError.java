package com.instant2fa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIError {
    @JsonProperty("status")
    int status;

    @JsonProperty("detail")
    String detail;

    @JsonProperty("title")
    String title;

    public APIError(String title, String detail, int status) {
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public APIError() { }

    @Override
    public String toString() {
        return "title=\"" + title + "\" detail=\"" + detail + "\" status=" + status;
    }
}
