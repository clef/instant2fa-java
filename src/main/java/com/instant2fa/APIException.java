package com.instant2fa;

import java.util.Arrays;

/**
 * Created by mark on 11/16/16.
 */
public class APIException extends Instant2FAException {
    public APIError[] errors;

    public APIException(APIError[] errors) {
        super("The following errors occurred: " + Arrays.toString(errors));
        this.errors = errors;
    }
}
