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
        int tmp = token.getTokenValue();
        if(tmp==0){
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
        }else{
            return tmp;
        }

    }
    
    public int getOneTimeToken(){
        return token.getTokenValue();
    }
    
    public void deleteOneTimeToken(){
        token.setTokenValue(0);
    }
    
    public boolean verificationOneTimeToken(int tokenPram){
        if(tokenPram==0 || tokenPram!=token.getTokenValue()){
            return false;
        }

        deleteOneTimeToken();
        return true;
    }
}
