package microservices.book.multiplication.event;

import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MultiplicationSolvedEvent implements Serializable {
    private final Long multiplicationResultAttemptId;
    private final Long useId;
    private final boolean correct;
}
