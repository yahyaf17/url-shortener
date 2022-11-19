package io.stargazer.urlshortener.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConflictErrorCode {

    URL_ALREADY_EXIST("4091", "Url Already Exist", "Url to-be Shortener Already Used"),
    CATEGORY_ALREADY_EXIST("4092", "Category already exist", "Category Name already exist");

    private String code;
    private String title;
    private String message;
}
