package io.stargazer.urlshortener.service.urlshort;

import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.model.entity.Category;
import io.stargazer.urlshortener.model.entity.UrlShort;
import io.stargazer.urlshortener.model.request.GetUrlRequest;
import io.stargazer.urlshortener.model.response.GetUrlResponse;
import io.stargazer.urlshortener.repository.CategoryRepository;
import io.stargazer.urlshortener.repository.UrlShortRepository;
import io.stargazer.urlshortener.util.NotFoundErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GetUrlService implements BaseService<GetUrlRequest, GetUrlResponse> {

    private UrlShortRepository urlShortRepository;
    private CategoryRepository categoryRepository;

    public GetUrlService(UrlShortRepository urlShortRepository, CategoryRepository categoryRepository) {
        this.urlShortRepository = urlShortRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GetUrlResponse invoke(GetUrlRequest input) {
        CommonUrlShortValidation.validateShortUrl(input.getUrlShort());

        UrlShort urlShort = urlShortRepository.findById(input.getUrlShort())
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND,
                        NotFoundErrorCode.URL_SHORT_NOT_FOUND.getCode(),
                        NotFoundErrorCode.URL_SHORT_NOT_FOUND.getTitle(),
                        NotFoundErrorCode.URL_SHORT_NOT_FOUND.getMessage())
                );

        String categoryName = null;
        if (Objects.nonNull(urlShort.getCategoryId())) {
            categoryName = categoryRepository.findById(urlShort.getCategoryId())
                    .orElse(Category.builder()
                            .name("")
                            .build())
                    .getName();
        }

        return GetUrlResponse.builder()
                .url(urlShort.getOriginalUrl())
                .category(categoryName)
                .userId(urlShort.getUserId())
                .build();
    }

}
