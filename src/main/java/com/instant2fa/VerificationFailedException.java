package com.instant2fa;

/**
 * Created by mark on 11/16/16.
 */
public class VerificationFailedException extends Instant2FAException {
    public VerificationFailedException(String message) {
        super(message);
    }
}
