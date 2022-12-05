package io.stargazer.urlshortener.service.urlshort;

import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.helper.BaseEntityHelper;
import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.model.entity.Category;
import io.stargazer.urlshortener.model.entity.UrlShort;
import io.stargazer.urlshortener.model.request.GetUrlShortenerCheckerRequest;
import io.stargazer.urlshortener.model.request.PostShortUrlRequest;
import io.stargazer.urlshortener.model.response.PostShortUrlResponse;
import io.stargazer.urlshortener.repository.CategoryRepository;
import io.stargazer.urlshortener.repository.UrlShortRepository;
import io.stargazer.urlshortener.service.category.CommonCategoryValidation;
import io.stargazer.urlshortener.util.NotFoundErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PostShortUrlService implements BaseService<PostShortUrlRequest, PostShortUrlResponse> {

    private CategoryRepository categoryRepository;
    private UrlShortRepository urlShortRepository;
    private GetUrlShortenerCheckerService getUrlShortenerCheckerService;

    public PostShortUrlService(CategoryRepository categoryRepository, UrlShortRepository urlShortRepository,
                               GetUrlShortenerCheckerService getUrlShortenerCheckerService) {
        this.categoryRepository = categoryRepository;
        this.urlShortRepository = urlShortRepository;
        this.getUrlShortenerCheckerService = getUrlShortenerCheckerService;
    }

    @Override
    public PostShortUrlResponse invoke(PostShortUrlRequest input) {
        CommonUrlShortValidation.validateShortUrl(input.getShortedUrl());
        getUrlShortenerCheckerService.invoke(GetUrlShortenerCheckerRequest.builder()
                .shortUrl(input.getShortedUrl())
                .build());

        Category category = getCategory(input);
        String categoryId = Objects.isNull(category) ? null : category.getId();

        UrlShort urlShort = UrlShort.builder()
                .urlShortId(input.getShortedUrl())
                .originalUrl(input.getOriginalUrl())
                .categoryId(categoryId)
                .build();
        BaseEntityHelper.initBaseEntity(urlShort);
        UrlShort savedUrlShort = urlShortRepository.save(urlShort);

        return PostShortUrlResponse.builder()
                .shortUrl(savedUrlShort.getUrlShortId())
                .build();
    }

    private Category getCategory(PostShortUrlRequest input) {
        Category category = null;
        if (Objects.nonNull(input.getCategoryId())) {
            category = categoryRepository.findById(input.getCategoryId())
                    .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND,
                            NotFoundErrorCode.CATEGORY_NOT_FOUND.getCode(),
                            NotFoundErrorCode.CATEGORY_NOT_FOUND.getTitle(),
                            NotFoundErrorCode.CATEGORY_NOT_FOUND.getMessage())
                    );
            CommonCategoryValidation.checkDeletedStatus(category.getDeleted());
        }

        if (Objects.isNull(category)
                && Objects.nonNull(input.getCategoryName())
                && Objects.nonNull(input.getUserId())) {
            category = findAndInsertCategoryIfPresent(input);
        }
        return category;
    }

    private Category findAndInsertCategoryIfPresent(PostShortUrlRequest input) {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByNameAndUserId(input.getCategoryId(), input.getUserId());
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        Category category = Category.builder()
                .name(input.getCategoryName())
                .userId(input.getUserId())
                .deleted(false)
                .build();
        BaseEntityHelper.initBaseEntity(category);
        return categoryRepository.save(category);
    }

}
