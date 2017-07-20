package server.app.extension;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationCodeClientVerificationFilter extends OncePerRequestFilter{
    
    private static final String DEFAULT_SELECT_STATEMENT = "select code, authentication from oauth_code where code = ?";
    private String selectAuthenticationSql = DEFAULT_SELECT_STATEMENT;
    
    private final JdbcTemplate jdbcTemplate;
    
    public AuthorizationCodeClientVerificationFilter(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = getUsername();
        String code = request.getParameter("code");
        
        OAuth2Authentication authentication = jdbcTemplate.queryForObject(selectAuthenticationSql,
                new RowMapper<OAuth2Authentication>() {
                    public OAuth2Authentication mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return SerializationUtils.deserialize(rs.getBytes("authentication"));
                    }
                }, code);
        
        if(!username.equals(authentication.getOAuth2Request().getClientId())){
            throw new InvalidRequestException("clientId error");
        }
        
        filterChain.doFilter(request, response);

    }
    
    private static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
            return (String) principal.toString();
        }
        return null;
    }
}
