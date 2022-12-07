package entity.users;


import entity.BaseEntity;
import entity.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;


@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public class Person extends BaseEntity<Long> {


    @Column(nullable = false)
    @NotEmpty
    private String firstname;
    @Column(nullable = false)
    @NotEmpty
    private String lastname;

    @Column(unique = true,nullable = false)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(unique = true,nullable = false)
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._]$")
    private String username;
    @Column(nullable = false)
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._$%^&*#!@\\-/\\\\]{8,}$")
    private String password;

    @CreationTimestamp
    private LocalDate dateAt;

    @CreationTimestamp
    private LocalTime timeAt;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Person(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
