package com.instant2fa;

/**
 * Created by mark on 11/16/16.
 */
public class VerificationMismatchException extends Instant2FAException {
    public VerificationMismatchException(String message) {
        super(message);
    }
}
