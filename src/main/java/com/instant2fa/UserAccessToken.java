package com.instant2fa;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

/**
 * Created by mark on 11/15/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Type("user-access-tokens")
class UserAccessToken {
    @Id
    String id;

    @JsonProperty("distinct_id")
    String distinctID;

    @JsonProperty("hosted_page_url")
    String hostedPageURL;
}
