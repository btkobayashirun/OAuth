package client.domain.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class TodoServiceImpl implements TodoService{
    
    @Inject
    @Named("todoAuthCodeGrantResourceRestTemplate")
    RestOperations restOperations; 
   
    @Value("http://localhost:8080/oauth-web/api/v1/todos")
    String uri;


    @Override
    public String getTodo() {
        String tmp = restOperations.getForObject(uri, String.class);
        return tmp;
    }

}
