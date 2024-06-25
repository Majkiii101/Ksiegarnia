package pl.zajecia.backendproject.shop.model.dto;


import lombok.Value;
import pl.zajecia.backendproject.shop.model.OrderItem;

public record OrderItemDto(Long id, String name, double price, int orderedQuantity) {
    public static OrderItemDto fromEntity(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getProduct().getName(),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity()
        );
    }
}
