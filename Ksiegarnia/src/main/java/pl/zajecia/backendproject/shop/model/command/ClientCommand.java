package pl.zajecia.backendproject.shop.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ClientCommand {
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String userName;
    private String password;
    private String adress;
    private String phoneNumber;


}

