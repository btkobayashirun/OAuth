package server.domain.auth.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.domain.auth.model.OAuthClient;
import server.domain.auth.repository.ClientRepository;

@Service("clientDetailsService")
@Transactional
public class OAuthClientDetailsService implements ClientDetailsService {
    
    @Inject
    ClientRepository clientRepository;

    @Value("http://localhost:8080")
    String redirectHost;

    @Override
    public ClientDetails loadClientByClientId(
            String clientId) throws ClientRegistrationException {

        OAuthClient client = clientRepository.findClientByClientId(clientId);
        if (client == null) {
            throw new NoSuchClientException("No client with requested id: "
                    + clientId);
        }
        Set<String> clientScopes = clientRepository.findClientScopesByClientId(
                clientId);
        Set<String> clientResources = clientRepository
                .findClientResourcesByClientId(clientId);
        Set<String> clientGrants = clientRepository.findClientGrantsByClientId(
                clientId);
        Set<String> clientRedirectUris = clientRepository
                .findClientRedirectUrisByClientId(clientId);

        OAuthClientDetails clientDetails = new OAuthClientDetails();
        clientDetails.setClientId(client.getClientId());
        clientDetails.setClientSecret(client.getClientSecret());
        clientDetails.setAccessTokenValiditySeconds(client
                .getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(client
                .getRefreshTokenValidity());
        clientDetails.setResourceIds(clientResources);
        clientDetails.setScope(clientScopes);
        clientDetails.setAuthorizedGrantTypes(clientGrants);

        Set<String> redirectUris = new HashSet<>();
        String redirectUri;
        for (String uri : clientRedirectUris) {
            redirectUri = redirectHost + "/" + uri;
            redirectUris.add(redirectUri);
        }
        clientDetails.setRegisteredRedirectUri(redirectUris);

        clientDetails.setClient(client);

        return clientDetails;
    }
}
