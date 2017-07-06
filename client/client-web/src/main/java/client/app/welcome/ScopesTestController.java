package client.app.welcome;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import client.domain.service.TodoService;

@Controller
@RequestMapping("/scopes")
public class ScopesTestController {
    
    @Inject
    TodoService todoservice;

    @RequestMapping(method = RequestMethod.GET)
    public String scopesGetRequest(Model model) {

        String result = todoservice.getTodo();
        
        model.addAttribute("result", result);

        return "oauth/view";
    }


}
