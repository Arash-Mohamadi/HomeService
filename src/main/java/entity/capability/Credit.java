package entity.capability;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Credit extends BaseAbility {
    @ColumnDefault("0")
    private double balance;


}
