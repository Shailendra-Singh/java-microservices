package me.shail.MyBoutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.MyBoutique.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
