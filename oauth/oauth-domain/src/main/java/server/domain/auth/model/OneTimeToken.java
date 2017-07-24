package server.domain.auth.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class OneTimeToken implements Serializable{

    private static final long serialVersionUID = 6825984659083611564L;
    
    private int tokenValue;
    
    public OneTimeToken(){
        tokenValue = 0;
    }

    public int getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(int tokenValue) {
        this.tokenValue = tokenValue;
    }
    
   /*
    * beanはok
    * 問題はfilter
    * ext auth filter
    * ext csrf filter
    * new onetime filter
    *　 　やり方はcsrf参照か？
    */

}
