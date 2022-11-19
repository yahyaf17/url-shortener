package io.stargazer.urlshortener.service.category;

import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.model.entity.Category;
import io.stargazer.urlshortener.model.request.PutCategoryRequest;
import io.stargazer.urlshortener.model.response.PutCategoryResponse;
import io.stargazer.urlshortener.repository.CategoryRepository;
import io.stargazer.urlshortener.util.BadRequestErrorCode;
import io.stargazer.urlshortener.util.NotFoundErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Service
public class PutCategoryService implements BaseService<PutCategoryRequest, PutCategoryResponse> {

    private final CategoryRepository categoryRepository;

    public PutCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PutCategoryResponse invoke(PutCategoryRequest input) {
        validateRequest(input);

        Category category = categoryRepository.findById(input.getId())
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND,
                        NotFoundErrorCode.CATEGORY_NOT_FOUND.getCode(),
                        NotFoundErrorCode.CATEGORY_NOT_FOUND.getTitle(),
                        NotFoundErrorCode.USER_NOT_FOUND.getMessage()));

        if (Objects.nonNull(input.getCategoryName())) {
            category.setName(input.getCategoryName());
        }
        if (Objects.nonNull(input.getCategoryDescription())) {
            category.setDescription(input.getCategoryDescription());
        }
        category.setUpdatedAt(Timestamp.from(Instant.now()));
        Category categoryUpdated = categoryRepository.save(category);

        return PutCategoryResponse.builder()
                .categoryName(categoryUpdated.getName())
                .categoryDescription(categoryUpdated.getDescription())
                .build();
    }

    private void validateRequest(PutCategoryRequest input) {
        if (Objects.isNull(input.getCategoryName()) && Objects.isNull(input.getCategoryDescription())) {
            throw new BaseException(HttpStatus.BAD_REQUEST,
                    BadRequestErrorCode.PUT_CATEGORY_REQUEST_NOT_VALID.getCode(),
                    BadRequestErrorCode.PUT_CATEGORY_REQUEST_NOT_VALID.getTitle(),
                    BadRequestErrorCode.PUT_CATEGORY_REQUEST_NOT_VALID.getMessage());
        }

        if (Objects.nonNull(input.getCategoryName())) {
            CommonCategoryValidation.validateCategoryName(input.getCategoryName());
        }
    }
}
