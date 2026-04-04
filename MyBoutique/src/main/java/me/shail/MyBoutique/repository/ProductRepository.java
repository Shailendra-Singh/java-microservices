package me.shail.MyBoutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.MyBoutique.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
