package io.stargazer.urlshortener.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadRequestErrorCode {

    CATEGORY_NAME_NOT_VALID("4001", "Category Name Not Valid",
            "Category name should not contain whitespace, use dash (-) or underscore (_) instead"),
    PUT_CATEGORY_REQUEST_NOT_VALID("4042", "Invalid Request",
            "One of category name or description is mandatory");

    private String code;
    private String title;
    private String message;
}
