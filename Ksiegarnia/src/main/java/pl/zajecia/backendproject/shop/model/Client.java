package pl.zajecia.backendproject.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String lastName;
    private LocalDate birthDate;
    private String userName;
    private String password;
    private String adress;
    private String phoneNumber;
    @OneToMany(mappedBy = "client")
    private Set<Order> orders = new HashSet<>();


    public Client(String name, String email, String lastName, LocalDate birthDate, String userName, String password, String adress, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userName = userName;
        this.password = password;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }
}
