package me.shail.order_service.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.order_service.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // public List<Order> findByCartCustomer_Id(Long id);
}
