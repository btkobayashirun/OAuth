package server.app.extension;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import server.domain.auth.service.OneTimeTokenService;

public class OneTimeTokenFilter extends OncePerRequestFilter {
    
    public static final RequestMatcher DEFAULT_ONE_TIME_TOKEN_MATCHER = new AntPathRequestMatcher("/login", "POST");
    private RequestMatcher requireOneTimeTokenProtectionMatcher = DEFAULT_ONE_TIME_TOKEN_MATCHER;
    
    @Inject
    OneTimeTokenService tokenService;
    
    private int token;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        request.setAttribute("onetimetoken", tokenService.generateOneTimeToken());
        
        if (!this.requireOneTimeTokenProtectionMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String str = request.getParameter("onetimetoken");
        if(str != null){
            token = Integer.parseInt(str);
        }
        if(!tokenService.verificationOneTimeToken(token)){
            throw new InvalidRequestException("OneTimeToken error");
        }
        
        filterChain.doFilter(request, response);        

    }

    public void setRequireCsrfProtectionMatcher(
            RequestMatcher requireCsrfProtectionMatcher) {
        Assert.notNull(requireCsrfProtectionMatcher,
                "requireCsrfProtectionMatcher cannot be null");
        this.requireOneTimeTokenProtectionMatcher = requireCsrfProtectionMatcher;
    }
    
}
