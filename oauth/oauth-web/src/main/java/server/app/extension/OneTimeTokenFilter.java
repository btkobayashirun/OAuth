package server.app.extension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import server.domain.auth.service.OneTimeTokenService;

public class OneTimeTokenFilter extends OncePerRequestFilter {
    
    @Inject
    OneTimeTokenService tokenService;
    
    private int token;
    
    @Value("${onetimetoken.url}")
    private String patternUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        int tokenTmp = tokenService.getOneTimeToken();
        
        request.setAttribute("onetimetoken", tokenService.generateOneTimeToken());
        
        for(AntPathRequestMatcher requestMatcher : getRequestMatcherList()){
            if (requestMatcher.matches(request)) {
                String str = request.getParameter("onetimetoken");
                if(str != null){
                    token = Integer.parseInt(str);
                }
                if(tokenTmp != token){
                    throw new InvalidRequestException("OneTimeToken error");
                }
                
                filterChain.doFilter(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
    
    public List<AntPathRequestMatcher> getRequestMatcherList(){
        String urlList[] = patternUrl.split(",",0);
        ArrayList<AntPathRequestMatcher> matcherList = new ArrayList<AntPathRequestMatcher>();
        for(String path : urlList){
            matcherList.add(new AntPathRequestMatcher(path, "POST"));
        }
        return matcherList;
    }
    
}
