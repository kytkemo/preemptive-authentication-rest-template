preemptive-authentication-rest-template
=======================================

An implementation of Spring’s RestTemplate with pre-emptive Basic and Digest authentication. Supports one authentication scheme per host.

## Install

Install the dependency to your local Maven repository with `mvn clean install`.

## Usage

Add the dependency to your project’s `pom.xml`.

```xml
<dependency>
    <groupId>com.kytkemo</groupId>
    <artifactId>preemptive-authentication-rest-template</artifactId>
    <version>1.1</version>
</dependency>
```

Create an instance of `PreemptiveAuthenticationRestTemplate` and set the required credentials. Apart from that, `PreemptiveAuthenticationRestTemplate` behaves same as the default implementation for `RestTemplate`.

```java
HttpHost host = new HttpHost("domain.com", 443, "https");

PreemptiveAuthenticationRestTemplate restTemplate = new PreemptiveAuthenticationRestTemplate();
restTemplate.setCredentials(host, PreemptiveAuthenticationScheme.BASIC_AUTHENTICATION, "username", "password");

Secret secret = restTemplate.getForObject("https://domain.com/secret/", Secret.class);
```
