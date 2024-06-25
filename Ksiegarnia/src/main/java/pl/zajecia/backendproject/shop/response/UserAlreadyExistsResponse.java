package pl.zajecia.backendproject.shop.response;

public class UserAlreadyExistsResponse {
    private String message;
    private String email;

    public UserAlreadyExistsResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }

    public UserAlreadyExistsResponse() {
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }
}
