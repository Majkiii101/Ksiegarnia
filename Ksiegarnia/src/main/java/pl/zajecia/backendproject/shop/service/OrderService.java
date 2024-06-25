package pl.zajecia.backendproject.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajecia.backendproject.shop.exception.OrderQuantityIsTooHighException;
import pl.zajecia.backendproject.shop.exception.ProductDontExistsException;
import pl.zajecia.backendproject.shop.model.Client;
import pl.zajecia.backendproject.shop.model.Order;
import pl.zajecia.backendproject.shop.model.OrderItem;
import pl.zajecia.backendproject.shop.model.Product;
import pl.zajecia.backendproject.shop.model.command.OrderItemCommand;
import pl.zajecia.backendproject.shop.repository.ClientRepository;
import pl.zajecia.backendproject.shop.repository.OrderItemRepository;
import pl.zajecia.backendproject.shop.repository.OrderRepository;
import pl.zajecia.backendproject.shop.repository.ProductRepository;
import pl.zajecia.backendproject.shop.model.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Order addOrder(Long clientId, List<OrderItemCommand> orderItems) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException("Client with that id dont exist"));
        Order order = new Order(LocalDateTime.now(), client, 0);
        orderRepository.save(order);

        double totalPrice = 0;
        for (OrderItemCommand orderItem : orderItems) {
//            TODO: Poprawić exception dla ProductDontExistsException (Szuka Id po "products: id" a nie po "orderItem: productId")
            Product product = productRepository.findById(orderItem.getProductId()).orElseThrow(() -> new ProductDontExistsException("Product with that id dont exist"));
            totalPrice = totalPrice + product.getPrice() * orderItem.getQuantity();
            OrderItem orderItem1 = new OrderItem(orderItem.getQuantity(), product, order);
            orderItemRepository.save(orderItem1);

      /*   Sprawdza czy ilość zamówionego produktu nie przekracza ilości produktu na stanie. Zapobiega też zamiawiana ilości = 0,
           jeżeli nie spełnia warunków uruchamia się wyjątek z odpowiednią wiadomościa */
            // TODO: Przy wyświetleniu wyjątku dodać jaką ilość ma produkt i ją wyświetlić (product.getQuantity())
            if (product.getQuantity() >= orderItem.getQuantity() && orderItem.getQuantity() != 0) {
                product.setQuantity(product.getQuantity() - orderItem.getQuantity());
            } else {
                throw new OrderQuantityIsTooHighException("Cannot order products with this quantity");
            }

            productRepository.save(product);
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);


        OrderDto orderDto = OrderDto.fromEntity(order);


        return order;
    }


}
