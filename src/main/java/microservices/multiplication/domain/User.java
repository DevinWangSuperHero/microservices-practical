package microservices.multiplication.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Entity
public final class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private final String alias;

    protected User(){
        alias = null;
    }
}
