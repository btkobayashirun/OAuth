package server.domain.auth.repository;

import java.util.Set;

import server.domain.auth.model.OAuthClient;

public interface ClientRepository {

    OAuthClient findClientByClientId(String clientId);

    Set<String> findClientScopesByClientId(String clientId);

    Set<String> findClientResourcesByClientId(String clientId);

    Set<String> findClientGrantsByClientId(String clientId);

    Set<String> findClientRedirectUrisByClientId(String clientId);

}
