package io.stargazer.urlshortener.helper;

import io.stargazer.urlshortener.base.BaseEntity;

import java.time.LocalDateTime;

public class BaseEntityHelper {

    private BaseEntityHelper() {
    }

    public static void initBaseEntity(BaseEntity baseEntity) {
        baseEntity.setCreatedAt(LocalDateTime.now());
        baseEntity.setUpdatedAt(LocalDateTime.now());
    }

}
