package io.stargazer.urlshortener.service.category;

import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.base.EmptyResponse;
import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.model.entity.Category;
import io.stargazer.urlshortener.model.request.DeleteCategoryRequest;
import io.stargazer.urlshortener.repository.CategoryRepository;
import io.stargazer.urlshortener.repository.UrlShortRepository;
import io.stargazer.urlshortener.util.NotFoundErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@Transactional
@Log4j2
public class DeleteCategoryService implements BaseService<DeleteCategoryRequest, EmptyResponse> {

    private CategoryRepository categoryRepository;
    private UrlShortRepository urlShortRepository;

    public DeleteCategoryService(CategoryRepository categoryRepository, UrlShortRepository urlShortRepository) {
        this.categoryRepository = categoryRepository;
        this.urlShortRepository = urlShortRepository;
    }

    @Override
    public EmptyResponse invoke(DeleteCategoryRequest input) {
        Category category = categoryRepository.findById(input.getId())
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND,
                        NotFoundErrorCode.CATEGORY_NOT_FOUND.getCode(),
                        NotFoundErrorCode.CATEGORY_NOT_FOUND.getTitle(),
                        NotFoundErrorCode.USER_NOT_FOUND.getMessage()));

        CommonCategoryValidation.checkDeletedStatus(category.getDeleted());

        category.setDeleted(true);
        category.setUpdatedAt(Timestamp.from(Instant.now()));
        Integer updatedUrl = urlShortRepository.updateCategoryByCategoryIdAndUserId(category.getUserId(),
                input.getId());
        categoryRepository.save(category);
        log.info("Url Updated {}", updatedUrl);

        return new EmptyResponse();
    }

}
