package entity.capability;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Credit extends BaseAbility {
    @ColumnDefault("0")
    private double balance;

//    @OneToMany(mappedBy = "credit")
//    @ToString.Exclude
//    private List<Transaction> transaction;


}
