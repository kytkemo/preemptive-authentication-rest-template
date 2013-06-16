package com.kytkemo.preemptiveauthenticationresttemplate.web.client;

import java.net.URI;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication extends HttpComponentsClientHttpRequestFactory {

    private BasicHttpContext context;
    private AuthCache cache;

    public HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication() {

        super();

        context = new BasicHttpContext();
        cache = new BasicAuthCache();

        context.setAttribute(ClientContext.AUTH_CACHE, cache);
    }

    public void setCredentials(HttpHost host,
                               PreemptiveAuthenticationScheme authenticationScheme,
                               String username,
                               String password) {

        AuthScheme scheme = null;

        if (authenticationScheme == PreemptiveAuthenticationScheme.BASIC_AUTHENTICATION) {
            scheme = new BasicScheme();
        } else if (authenticationScheme == PreemptiveAuthenticationScheme.DIGEST_AUTHENTICATION) {
            scheme = new DigestScheme();
        }

        cache.put(host, scheme);

        final DefaultHttpClient client = (DefaultHttpClient) getHttpClient();
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);

        client.getCredentialsProvider().setCredentials(new AuthScope(host), credentials);
    }

    @Override
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {

        return context;
    }
}
