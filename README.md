# cucumber-java-appium-app-browserstack

This repository demonstrates how to run Appium tests in Cucumber Testng on BrowserStack App Automate using BrowserStack SDK.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Setup

### Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

3. Gradle
  - If Gradle is not downloaded, download it from [here](https://gradle.org/releases/)  
  - For installation, follow the instructions [here](https://gradle.org/install/) 

### Install the dependencies

To install the dependencies for Android tests, run :
```sh
cd android/
mvn clean
```

Or,

To install the dependencies for iOS tests, run :

```sh
cd ios/
mvn clean
```

## Getting Started

Getting Started with Appium tests in Cucumber TestNg on BrowserStack couldn't be easier!

### **Run Sample test :**

- Switch to one of the following directories: [Android examples](android) or [iOS examples](ios)
- Run the following maven command `mvn test -P sample-test` to run in maven enviroment.
- Run the following gradle command `gradle clean sampleTest` to run in gradle enviroment.

### **Use Local testing for apps that access resources hosted in development or testing environments :**

- Simply configure the `browserstackLocal` parameter in the `browserstack.yml` file accordingly in [Android examples](android) or [iOS examples](ios).
  ```
  browserstackLocal: true
  ```
- You can use the `LocalSample` app provided in both folder [Android examples](android) or [iOS examples](ios) to run your test. Change the app parameter in the `browserstack.yml` file and run the tests with the following command: `mvn test -P sample-local-test`


**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)

## Integration with other Java frameworks

For other Java frameworks samples, refer to following repositories :

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [Java](https://github.com/browserstack/java-appium-app-browserstack)

Note: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).