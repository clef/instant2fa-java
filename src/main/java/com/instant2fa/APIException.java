package com.instant2fa;

import java.util.Arrays;

public class APIException extends Instant2FAException {
    public APIError[] errors;

    public APIException(APIError[] errors) {
        super("The following errors occurred: " + Arrays.toString(errors));
        this.errors = errors;
    }
}
