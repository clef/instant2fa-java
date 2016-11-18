# instant2fa-java

A Java / Scala client for [Instant2FA](https://instant2fa.com). Compatible with Java 7 and above.

## Installation

This library is hosted on jcenter, so install it in the usual way:

`build.gradle`: 

```
compile 'com.instant2fa:instant2fa:1.0.1'
```

`build.sbt`:

```
libraryDependencies += "com.instant2fa" % "instant2fa" % "1.0.1",
```

## Usage

See our [integration guide](http://docs.instant2fa.com/).

Here's a flavor of the methods you'll be using:

```java

Instant2FA instant2fa = new Instant2FA(ACCESS_KEY, ACCESS_SECRET);

String distinctID = "A_UNIQUE_ID_FOR_A_USER";

// To show hosted 2FA settings:
String settingsPageURL = instant2fa.createSettings(distinctID);

// To show a hosted verification page:
try {
    String verificationPageURL = instant2fa.createVerification(distinctID);
    // Redirect to 2FA verfication page
} catch (MFANotEnabledException e) {
    // Log the user in as normal
}

// To see whether a user successfully completed 2FA verification:
boolean isConfirmed = instant2fa.confirmVerification(distinctID, token);

```

One thing to note is that the above methods make synchronous requests. To use them asynchronously, wrap them in futures: 

```java
CompletionStage<String> settingsPageURLPromise = CompletableFuture.supplyAsync(() -> instant2fa.createSettings(distinctID));
```
