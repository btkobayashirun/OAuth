package server.domain.auth.service;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import server.domain.auth.model.OAuthClient;

public class OAuthClientDetails extends BaseClientDetails {

    private static final long serialVersionUID = 1L;

    private OAuthClient client;

    public void setClient(OAuthClient client) {
        this.client = client;
    }

    public OAuthClient getClient() {
        return this.client;
    }

}
