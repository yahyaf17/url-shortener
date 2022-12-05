package io.stargazer.urlshortener.model.entity;

import io.stargazer.urlshortener.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "url_short")
public class UrlShort extends BaseEntity {

    @Id
    @Column(name = "url_short_id")
    private String urlShortId;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "userId")
    private String userId;

}
