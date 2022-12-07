package entity.users;


import entity.capability.*;
import entity.enums.SpecialistStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Specialist extends Person {

    @Column(nullable = false)
    @ColumnDefault("0")
    private double scoreAvg;

    @Enumerated(EnumType.STRING)
    private SpecialistStatus status;

    @Lob
//    @Column(columnDefinition="BLOB")
    private byte[] photo;

    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}) //unidirectional
    @ToString.Exclude
    private Credit credit;

    @OneToMany(mappedBy = "specialist",cascade = CascadeType.REMOVE) // bidirectional
    @ToString.Exclude
    private List<Opinion> opinions;

    @OneToMany(mappedBy = "specialist",cascade = CascadeType.REMOVE) // bidirectional
    @ToString.Exclude
    private List<Suggestion> suggestions;

    @ManyToMany //bidirectional
    @ToString.Exclude
    private Set<Services> servicesSet;

    @ManyToMany //bidirectional
    @ToString.Exclude
    private Set<SubServices> subServicesSet;

    public Specialist(String firstname, String lastname, String email, String username, String password, byte[] photo) {
        super(firstname, lastname, email, username, password);
        this.photo = photo;
    }
}
