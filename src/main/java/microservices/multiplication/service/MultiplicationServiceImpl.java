package microservices.multiplication.service;

import microservices.multiplication.domain.Multiplication;
import microservices.multiplication.domain.MultiplicationResultAttempt;
import microservices.multiplication.domain.User;
import microservices.multiplication.event.EventDispatcher;
import microservices.multiplication.event.MultiplicationSolvedEvent;
import microservices.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private RandomGeneratorService randomGeneratorService;
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;
    private UserRepository userRepository;
    private EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService, MultiplicationResultAttemptRepository multiplicationResultAttemptRepository, UserRepository userRepository, EventDispatcher eventDispatcher) {
        this.randomGeneratorService = randomGeneratorService;
        this.multiplicationResultAttemptRepository = multiplicationResultAttemptRepository;
        this.userRepository = userRepository;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    @Transactional
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());
        boolean correct = resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA() *
                resultAttempt.getMultiplication().getFactorB();
        Assert.isTrue(!resultAttempt.isCorrect(), "you can not send an attempt marked as correct!!");
        MultiplicationResultAttempt checkAttemp = new MultiplicationResultAttempt(user.orElse(resultAttempt.getUser()), resultAttempt.getMultiplication(), resultAttempt.getResultAttempt(), correct);
        multiplicationResultAttemptRepository.save(checkAttemp);
        eventDispatcher.send(new MultiplicationSolvedEvent(
                checkAttemp.getId(), checkAttemp.getUser().getId(), checkAttemp.isCorrect()
        ));
        return correct;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatesForUser(String userAlias) {
        return multiplicationResultAttemptRepository.findTop5ByUser_AliasOrderByIdDesc(userAlias);
    }
}
