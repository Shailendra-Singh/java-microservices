package me.shail.MyBoutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.MyBoutique.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
