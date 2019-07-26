package microservices.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${multiplication.exchange}")
    private String multiplicationExchange;

    @Value("${multiplication.solved.key}")
    private String multiplicationSolvedRoutingKey;

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey, multiplicationSolvedEvent);
    }
}
