package server.domain.auth.service;

public interface OneTimeTokenService {
    
    int generateOneTimeToken();
    
    int getOneTimeToken();

}
