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
    private LocalDate dateAt;
    @CreationTimestamp
    private LocalTime timeAt;
    private double price;
    private LocalTime startWork;
    private LocalTime endWork;

    private String day;

    @ToString.Exclude
    @ManyToOne
    private Specialist specialist;
    @ToString.Exclude
    @ManyToOne
    private Order order;

    public Suggestion(double price, LocalTime startWork, LocalTime endWork,String day) {
        this.price = price;
        this.startWork = startWork;
        this.endWork = endWork;
        this.day =day;
    }
}
