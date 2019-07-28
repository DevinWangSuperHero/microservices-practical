package microservices.multiplication.event;

import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MultiplicationSolvedEvent implements Serializable {
    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;
}
