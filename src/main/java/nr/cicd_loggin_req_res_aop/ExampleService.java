package nr.cicd_loggin_req_res_aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    private static final Logger log = LoggerFactory.getLogger(ExampleService.class);

    public String processMethod(String in) {
        log.info("Procesando entrada: {}", in);

        String rs = "Procesado " + in;
        log.debug("Resultado intermedio: {}", rs);

        rs += " exitosamente";

        log.info("Resultado final: {}", rs);

        return rs;
    }

}
