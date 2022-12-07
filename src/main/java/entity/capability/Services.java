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
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Services extends BaseAbility {
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "services",cascade = CascadeType.REMOVE)
    private List<SubServices> subList;

    @ToString.Exclude
    @ManyToMany(mappedBy = "servicesSet")
    private Set<Specialist> specialistSet;

    public void addSpecialist(Specialist specialist){
        this.getSpecialistSet().add(specialist);
        specialist.getServicesSet().add(this);
    }

    public Services(String name) {
        this.name = name;
    }
}
