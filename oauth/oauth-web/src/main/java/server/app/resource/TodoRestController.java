package server.app.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoRestController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTodo() {
        return "GET OK";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String postTodo() {
        return "post OK";
    }

}
