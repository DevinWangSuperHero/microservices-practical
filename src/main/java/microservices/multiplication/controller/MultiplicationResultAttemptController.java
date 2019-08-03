package microservices.multiplication.controller;

import com.netflix.discovery.converters.Auto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import microservices.multiplication.domain.MultiplicationResultAttempt;
import microservices.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@Slf4j
public final class MultiplicationResultAttemptController {

    @Autowired
    private MultiplicationService multiplicationService;

    @Value("${server.port")
    private String port;

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        log.info("start postResult method");
        boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
        MultiplicationResultAttempt multiplicationResultAttempt1 = new MultiplicationResultAttempt(multiplicationResultAttempt.getUser(), multiplicationResultAttempt.getMultiplication(), multiplicationResultAttempt.getResultAttempt(), isCorrect);
        return ResponseEntity.ok(multiplicationResultAttempt1);
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistic(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(multiplicationService.getStatesForUser(alias));
    }

    @GetMapping
    @RequestMapping("/{resultId}")
    ResponseEntity<MultiplicationResultAttempt> getResultById(@PathVariable("resultId") Long resultId) {
        log.info("retrieving result {} from server");
        return ResponseEntity.ok(multiplicationService.getResultById(resultId));
    }

    @RequiredArgsConstructor
    @ToString
    @Getter
    public static final class ResultResponse {
        private final boolean correct;
    }
}
