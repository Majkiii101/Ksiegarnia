package pl.zajecia.backendproject.shop.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginCommand {
    private String email;
    private String password;
}
