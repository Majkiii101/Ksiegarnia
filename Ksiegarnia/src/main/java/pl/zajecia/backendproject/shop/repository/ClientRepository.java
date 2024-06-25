package pl.zajecia.backendproject.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zajecia.backendproject.shop.model.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);

    @Query("select c from Client c where c.email = :email")
    Optional<Client> findByEmail(@Param("email") String email);

    @Query("select c from Client c " +
            "left join fetch c.orders o " +
            "left join fetch o.orderItems oi " +
            "left join fetch oi.product p " +
            "where c.id = :clientId")
    Optional<Client> findByIdAndFetchOrders(@Param("clientId") Long clientId);

    @Query("select c from Client c " +
            "left join fetch c.orders o " +
            "left join fetch o.orderItems oi " +
            "left join fetch oi.product p " +
            "where c.id = :clientId" +
            " order by o.orderDate desc limit 1")
    Optional<Client> findByIdAndFetchLastOrder(@Param("clientId") Long clientId);

}
