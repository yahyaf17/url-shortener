package io.stargazer.urlshortener.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotFoundErrorCode {
    USER_NOT_FOUND("4041", "User not found", "User not found"),
    CATEGORY_NOT_FOUND("4042", "Category not found", "Cateogory not found");

    private String code;
    private String title;
    private String message;
}
