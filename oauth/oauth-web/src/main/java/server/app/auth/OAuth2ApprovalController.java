package server.app.auth;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import server.domain.auth.service.OneTimeTokenService;

@Controller
public class OAuth2ApprovalController {
    
    @Inject OneTimeTokenService oneTimeTokenService;

    @RequestMapping("/oauth/confirm_access")
    public String confirmAccess() {
        return "oauth/oauthConfirm";
    }
    
}
