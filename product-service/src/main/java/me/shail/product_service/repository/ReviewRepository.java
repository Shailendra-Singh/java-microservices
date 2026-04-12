package me.shail.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.product_service.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
