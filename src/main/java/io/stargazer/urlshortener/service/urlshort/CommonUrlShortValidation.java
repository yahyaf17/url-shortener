package io.stargazer.urlshortener.service.urlshort;

import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.util.BadRequestErrorCode;
import org.springframework.http.HttpStatus;

public class CommonUrlShortValidation {

    private CommonUrlShortValidation() {
    }

    public static void validateShortUrl(String shortUrl) {
        if (Character.isDigit(shortUrl.charAt(0))) {
            throw new BaseException(HttpStatus.BAD_REQUEST,
                    BadRequestErrorCode.SHORT_URL_START_WITH_NUMBER.getCode(),
                    BadRequestErrorCode.SHORT_URL_START_WITH_NUMBER.getTitle(),
                    BadRequestErrorCode.SHORT_URL_START_WITH_NUMBER.getMessage());
        }
    }

}
