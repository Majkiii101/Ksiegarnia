package pl.zajecia.backendproject.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zajecia.backendproject.shop.model.Client;
import pl.zajecia.backendproject.shop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

//   @Query("select c from Client c join fetch c.orders o left join fetch o.orderItems where c.id = :id")
//   Optional<Client> findByIdAndFetchOrders(@Param("id") Long id);

}
