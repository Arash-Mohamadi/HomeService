package entity.capability;

import entity.enums.OrderStatus;
import entity.users.Customer;
import entity.users.Specialist;
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
    private LocalDate creationDate;

    @CreationTimestamp
    private LocalTime creationTime;

    private double price;

    private LocalTime startWork;
    private LocalTime endWork;

    private String day;

    private String description;
    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ToString.Exclude
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
    private List<Suggestion> suggestions;

    @ToString.Exclude
    @ManyToOne //bidirectional
    private Customer customer;

    @ManyToOne //bidirectional
    @ToString.Exclude
    private SubServices subServices;


    @OneToOne //bidirectional
    @ToString.Exclude
    private Opinion opinion;

    @ManyToOne //bidirectional
    @ToString.Exclude
    private Specialist specialist;

    public void addSuggestion(Suggestion suggestion){
        this.getSuggestions().add(suggestion);
        suggestion.setOrder(this);
    }

    public void setOrderWithOpinion(Opinion opinion){
        this.setOpinion(opinion);
        opinion.setOrder(this);
    }
    public Order(double price, LocalTime startWork, LocalTime endWork, String day, String description, String address) {
        this.price = price;
        this.startWork = startWork;
        this.endWork = endWork;
        this.day = day;
        this.description = description;
        this.address = address;
    }
}
