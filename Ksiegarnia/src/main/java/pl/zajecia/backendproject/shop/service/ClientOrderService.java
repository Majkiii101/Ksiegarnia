package pl.zajecia.backendproject.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajecia.backendproject.shop.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class ClientOrderService {

    private final OrderItemRepository repository;
}
