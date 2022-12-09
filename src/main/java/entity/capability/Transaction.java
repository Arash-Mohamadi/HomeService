package entity.capability;

import entity.enums.TransactionStatus;
import entity.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;

//@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Transaction extends BaseAbility{

    private TransactionStatus transactionStatus;
    private TransactionType transactionType;
    private double amount;
    @CreationTimestamp
    private LocalTime time;

    @ManyToOne
    @ToString.Exclude
    private Credit credit;

}
