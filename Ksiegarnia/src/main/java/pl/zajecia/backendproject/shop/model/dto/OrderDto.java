package pl.zajecia.backendproject.shop.model.dto;

import lombok.Value;
import pl.zajecia.backendproject.shop.model.Order;
import pl.zajecia.backendproject.shop.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record OrderDto(long id, List<OrderItemDto> orderItemDtos, LocalDateTime orderDate, double totalPrice) {
    public static OrderDto fromEntity(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderItems().stream().map(OrderItemDto::fromEntity).toList(),
                order.getOrderDate(),
                order.getTotalPrice());
    }

}
