package microservices.multiplication.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventDispatcher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${multiplication.exchange}")
    private String multiplicationExchange;

    @Value("${multiplication.solved.key}")
    private String multiplicationSolvedRoutingKey;

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        log.info("start send message: {}", multiplicationSolvedEvent.toString());
        rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey, multiplicationSolvedEvent);
    }
}
