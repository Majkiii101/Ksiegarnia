package pl.zajecia.backendproject.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import pl.zajecia.backendproject.shop.exception.ProductCannotBeEmptyException;
import pl.zajecia.backendproject.shop.exception.ProductDontExistsException;
import pl.zajecia.backendproject.shop.model.Product;
import pl.zajecia.backendproject.shop.model.command.ProductCommand;
import pl.zajecia.backendproject.shop.model.dto.ProductDto;
import pl.zajecia.backendproject.shop.response.ProductCannotBeEmptyResponse;
import pl.zajecia.backendproject.shop.response.ProductDontExistsResponse;
import pl.zajecia.backendproject.shop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")

public class ProductController {

    private final ProductService productService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductCommand command) {
        productService.addProduct(command);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductCommand command, @PathVariable Long id) {
        Product product = productService.updateProduct(id, command);
        ProductDto productDto = ProductDto.fromEntity(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> gettingProducts() {
        List<Product> products = productService.gettingProducts();
        simpMessagingTemplate.convertAndSend("/topic/greetings", "Hello");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ExceptionHandler(ProductCannotBeEmptyException.class)
    public ResponseEntity<ProductCannotBeEmptyResponse> handleProductCannotBeEmptyException(ProductCannotBeEmptyException e) {
        return new ResponseEntity<>(new ProductCannotBeEmptyResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductDontExistsException.class)
    public ResponseEntity<ProductDontExistsResponse> handleProductDontExistsException(ProductDontExistsException e) {
        return new ResponseEntity<>(new ProductDontExistsResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
