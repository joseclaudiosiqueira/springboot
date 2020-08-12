package com.joseclaudiosiqueira.springboot.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Transactional(readOnly = true)
	// @Query("SELECT DISTINCT product FROM Product product INNER JOIN
	// product.categories category WHERE product.name LIKE %:name% AND category IN
	// :categories")
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> category,
			Pageable pageRequest);
	// @Param("name")
	// @Param("categories")
}
