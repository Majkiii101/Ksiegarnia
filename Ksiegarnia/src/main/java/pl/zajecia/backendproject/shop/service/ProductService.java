package pl.zajecia.backendproject.shop.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajecia.backendproject.shop.exception.ProductCannotBeEmptyException;
import pl.zajecia.backendproject.shop.exception.ProductDontExistsException;
import pl.zajecia.backendproject.shop.model.Product;
import pl.zajecia.backendproject.shop.model.command.ProductCommand;
import pl.zajecia.backendproject.shop.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;


    @PostConstruct

    public void init() {
        repository.saveAndFlush(new Product("Dlugopis", 5.0, 100));
        repository.saveAndFlush(new Product("Zeszyt", 6.0, 199));
        repository.saveAndFlush(new Product("Plecak", 3.0, 50));
        repository.saveAndFlush(new Product("Olowek", 11.0, 10));
        repository.saveAndFlush(new Product("Krzyzacy", 88.0, 23));
    }


    public void addProduct(ProductCommand command) {
        if (command.getName() == null) throw new ProductCannotBeEmptyException("name cannot be null");
        if (command.getPrice() == 0.0) throw new ProductCannotBeEmptyException("price cannot be null");
        if (command.getQuantity() == 0) throw new ProductCannotBeEmptyException("quantity cannot be null");

        Product product = new Product(command.getName(), command.getPrice(), command.getQuantity());
        repository.saveAndFlush(product);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductDontExistsException("cannot find product"));
    }

    public Product updateProduct(Long id, ProductCommand command) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductDontExistsException("cannot find product"));
        product.setName(command.getName());
        product.setPrice(command.getPrice());
        product.setQuantity(command.getQuantity());
        repository.saveAndFlush(product);
        return product;
    }

    public List<Product> gettingProducts() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) throw new ProductDontExistsException("cannot find products");
        return products;
    }
}
