# preemptive-authentication-rest-template

An implementation of Spring’s RestTemplate with pre-emptive Basic and Digest authentication. Supports one authentication
scheme per host.

## Usage

```java
HttpHost host = new HttpHost("domain.com", 443, "https");
PreemptiveAuthenticationRestTemplate restTemplate = new PreemptiveAuthenticationRestTemplate();

restTemplate.setCredentials(host, PreemptiveAuthenticationScheme.BASIC_AUTHENTICATION, "username", "password");
```
