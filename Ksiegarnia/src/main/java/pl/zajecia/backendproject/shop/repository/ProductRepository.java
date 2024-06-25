package pl.zajecia.backendproject.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zajecia.backendproject.shop.model.OrderItem;
import pl.zajecia.backendproject.shop.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);

    @Query("select p from Product p where p.id = :id")
    Optional<Product>findById(@Param("id") Long id);
}
