package entity.capability;

import entity.users.Specialist;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubServices extends BaseAbility {
    private double basePrice;
    private String description;

    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String name;

    @ToString.Exclude
    @ManyToOne
    private Services services;

    @ToString.Exclude
    @ManyToMany(mappedBy = "subServicesSet")
    private Set<Specialist> specialistSet;

    @ToString.Exclude
    @OneToMany(mappedBy = "subServices")
    private List<Order> orders;

    public void addSpecialist(Specialist specialist){
        this.getSpecialistSet().add(specialist);
        specialist.getSubServicesSet().add(this);
    }

    public SubServices(double basePrice, String description, String name) {
        this.basePrice = basePrice;
        this.description = description;
        this.name = name;
    }
}
