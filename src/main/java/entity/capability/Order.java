package entity.capability;

import entity.enums.OrderStatus;
import entity.users.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseAbility {
    @CreationTimestamp
    private LocalDate dateAt;

    @CreationTimestamp
    private LocalTime timeAt;

    private double price;

    private String description;
    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ToString.Exclude
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
    private List<Suggestion> suggestions;

    @ToString.Exclude
    @ManyToOne
    private Customer customer;

    public Order(double price, String description, String address) {
        this.price = price;
        this.description = description;
        this.address = address;
    }
}
