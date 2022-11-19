package io.stargazer.urlshortener.helper;

import io.stargazer.urlshortener.base.BaseEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class BaseEntityHelper {

    private BaseEntityHelper() {
    }

    public static void initBaseEntity(BaseEntity baseEntity) {
        baseEntity.setCreatedAt(Timestamp.from(Instant.now()));
        baseEntity.setUpdatedAt(Timestamp.from(Instant.now()));
    }

}
