package nr.cicd_loggin_req_res_aop;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private ExampleService service;

    @GetMapping("process")
    public String process(@RequestParam(value = "in", defaultValue = "hola") String in) {
        return service.processMethod(in);
    }

    @PostMapping("process")
    public Todo processTodo(@RequestBody Todo entity) {
        return entity;
    }

    public record Todo(String name, String description, LocalDateTime date, boolean isOk) {}

}
