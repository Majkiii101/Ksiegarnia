package pl.zajecia.backendproject.shop.response;

public class ProductCannotBeEmptyResponse {
    private String message;

    public ProductCannotBeEmptyResponse(String message) {
        this.message = message;
    }

    public ProductCannotBeEmptyResponse(){

    }
    public String getMessage() {
        return message;
    }
}
