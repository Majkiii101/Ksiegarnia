package pl.zajecia.backendproject.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajecia.backendproject.shop.exception.OrderQuantityIsTooHighException;
import pl.zajecia.backendproject.shop.model.Order;
import pl.zajecia.backendproject.shop.model.command.OrderItemCommand;
import pl.zajecia.backendproject.shop.model.dto.OrderDto;
import pl.zajecia.backendproject.shop.response.OrderQuantityIsTooHighResponse;
import pl.zajecia.backendproject.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/clients/{clientId}")
    public ResponseEntity<OrderDto> addOrder(@PathVariable Long clientId, @RequestBody List<OrderItemCommand> orderItems) {
        Order order = orderService.addOrder(clientId, orderItems);
        OrderDto orderDto = OrderDto.fromEntity(order);
        return new ResponseEntity<>(orderDto, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(OrderQuantityIsTooHighException.class)
    public ResponseEntity<OrderQuantityIsTooHighResponse> handleOrderQuantityIsTooHighException(OrderQuantityIsTooHighException e) {
        return new ResponseEntity<>(new OrderQuantityIsTooHighResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
