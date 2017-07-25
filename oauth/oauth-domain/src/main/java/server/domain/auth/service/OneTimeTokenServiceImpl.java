package server.domain.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import server.domain.auth.model.OneTimeToken;

@Service
public class OneTimeTokenServiceImpl implements OneTimeTokenService{

    private byte bytes[] = new byte[32];
    
    @Inject
    OneTimeToken token;
    
    public int generateOneTimeToken(){
    
        SecureRandom random = new SecureRandom();
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        random.nextBytes(bytes);
        int num = random.nextInt();
        
        token.setTokenValue(num);
        return num;

    }
    
    public int getOneTimeToken(){
        return token.getTokenValue();
    }

}
