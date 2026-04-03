package me.shail.MyBoutique;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByStatus(CartStatus status);
    List<Cart> findByStatusAndCustomer(CartStatus status, Customer customer);
}
