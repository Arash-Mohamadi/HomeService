package entity.capability;

import entity.users.Specialist;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Suggestion extends BaseAbility {
    @CreationTimestamp
    private LocalDate creationDate;
    @CreationTimestamp
    private LocalTime creationTime;
    private double price;

    private double duration; //  unit of hour

    @ToString.Exclude
    @ManyToOne  //bidirectional
    private Specialist specialist;
    @ToString.Exclude
    @ManyToOne //bidirectional
    private Order order;

    public Suggestion(double price, double duration) {
        this.price = price;
        this.duration = duration;
    }
}
