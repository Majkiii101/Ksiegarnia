package pl.zajecia.backendproject.shop.response;

public class OrderQuantityIsTooHighResponse {
    private String message;

    public OrderQuantityIsTooHighResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
