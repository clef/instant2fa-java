package com.instant2fa;

public class VerificationMismatchException extends Instant2FAException {
    public VerificationMismatchException(String message) {
        super(message);
    }
}
