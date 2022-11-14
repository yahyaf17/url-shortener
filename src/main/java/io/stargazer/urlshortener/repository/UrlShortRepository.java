package io.stargazer.urlshortener.repository;

import io.stargazer.urlshortener.model.entity.UrlShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortRepository extends JpaRepository<UrlShort, String> {

    @Query(value = "SELECT u.shortUrl FROM UrlShort u " +
            "WHERE u.shortUrl = :shortUrl")
    Optional<String> findByShortUrl(String shortUrl);

}
