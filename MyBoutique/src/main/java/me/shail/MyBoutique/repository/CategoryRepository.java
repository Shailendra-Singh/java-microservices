package me.shail.MyBoutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.shail.MyBoutique.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
