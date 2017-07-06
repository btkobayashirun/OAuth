package server.app.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuth2ApprovalController {

    @RequestMapping("/oauth/confirm_access")
    public String confirmAccess() {
        return "oauth/oauthConfirm";
    }

}
