package io.stargazer.urlshortener.repository;

import io.stargazer.urlshortener.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(String name);

    Optional<Category> findCategoryByNameAndUserId(String id, String userId);

    @Query(value = "SELECT * FROM category " +
            "WHERE user_id = :userId " +
            "ORDER BY updated_at DESC", nativeQuery = true)
    Page<Category> findCategoriesByUserIdPage(Pageable pageable, String userId);

}
