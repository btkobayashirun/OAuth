package server.domain.auth.service;

public interface OneTimeTokenService {
    
    int generateOneTimeToken();
    
    void deleteOneTimeToken();
    
    int getOneTimeToken();
    
    boolean verificationOneTimeToken(int token);

}
