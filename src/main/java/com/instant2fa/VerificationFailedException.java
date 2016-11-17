package com.instant2fa;

public class VerificationFailedException extends Instant2FAException {
    public VerificationFailedException(String message) {
        super(message);
    }
}
