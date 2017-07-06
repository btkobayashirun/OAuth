package server.app.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuth2ErrorController {

    @RequestMapping("/oauth/error")
    public String handleError() {
        return "common/error/oauthError";
    }

}