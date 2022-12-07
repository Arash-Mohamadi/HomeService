package entity.capability;

import entity.users.Customer;
import entity.users.Specialist;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Opinion extends BaseAbility {
    @Column(nullable = false)
    private double score;
    private String comment;

    @ToString.Exclude
    @ManyToOne //bidirectional
    private Customer customer;

    @ToString.Exclude
    @ManyToOne //bidirectional
    private Specialist specialist;

    @OneToOne
    @ToString.Exclude
    private Order order;

    public Opinion(Integer score) {
        this.score = score;
    }

    public Opinion(Integer score, String comment) {
        this.score = score;
        this.comment = comment;
    }
}
