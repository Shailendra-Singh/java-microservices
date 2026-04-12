package me.shail.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.order_service.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
