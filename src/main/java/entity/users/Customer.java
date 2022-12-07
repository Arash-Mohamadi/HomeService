package entity.users;


import entity.capability.Credit;
import entity.capability.Opinion;
import entity.capability.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Customer extends  Person{
    @ToString.Exclude
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}) // unidirectional
    private Credit credit;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE) //bidirectional
    @ToString.Exclude
    private List<Opinion> opinions;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE) //bidirectional
    private List<Order> orders;

    public Customer(String firstname, String lastname, String email, String username, String password) {
        super(firstname, lastname, email, username, password);
    }


}
