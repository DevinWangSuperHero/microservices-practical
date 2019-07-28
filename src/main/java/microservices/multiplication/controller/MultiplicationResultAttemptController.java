package microservices.multiplication.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import microservices.multiplication.domain.MultiplicationResultAttempt;
import microservices.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
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
        return ResponseEntity.ok(multiplicationService.getResultById(resultId));
    }

    @RequiredArgsConstructor
    @ToString
    @Getter
    public static final class ResultResponse {
        private final boolean correct;
    }
}
