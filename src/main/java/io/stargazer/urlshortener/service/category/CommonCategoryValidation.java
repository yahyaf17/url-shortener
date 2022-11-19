package io.stargazer.urlshortener.service.category;

import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.util.BadRequestErrorCode;
import io.stargazer.urlshortener.util.ConflictErrorCode;
import org.springframework.http.HttpStatus;

public class CommonCategoryValidation {

    private CommonCategoryValidation() {
    }

    public static void validateCategoryName(String categoryName) {
        if (categoryName.contains(" ")) {
            throw new BaseException(HttpStatus.BAD_REQUEST,
                    BadRequestErrorCode.CATEGORY_NAME_NOT_VALID.getCode(),
                    BadRequestErrorCode.CATEGORY_NAME_NOT_VALID.getTitle(),
                    BadRequestErrorCode.CATEGORY_NAME_NOT_VALID.getMessage());
        }
    }

    public static void checkDeletedStatus(Boolean deletedStatus) {
        if (Boolean.TRUE.equals(deletedStatus)) {
            throw new BaseException(HttpStatus.CONFLICT,
                    ConflictErrorCode.CATEGORY_ALREADY_DELETED.getCode(),
                    ConflictErrorCode.CATEGORY_ALREADY_DELETED.getTitle(),
                    ConflictErrorCode.CATEGORY_ALREADY_DELETED.getMessage());
        }
    }
}
