package microservices.multiplication.controller;

import lombok.extern.slf4j.Slf4j;
import microservices.multiplication.domain.Multiplication;
import microservices.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/multiplications")
public class MultiplicationController {

    @Autowired
    private MultiplicationService multiplicationService;

    @Value("${server.port}")
    private String port;



    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        log.info("generating a random multiplication from server @ {}", port);
        return multiplicationService.createRandomMultiplication();
    }
}
