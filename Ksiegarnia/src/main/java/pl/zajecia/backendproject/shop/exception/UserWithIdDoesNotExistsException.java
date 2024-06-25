package pl.zajecia.backendproject.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithIdDoesNotExistsException extends RuntimeException {
    private String message;
    private Long id;
}
