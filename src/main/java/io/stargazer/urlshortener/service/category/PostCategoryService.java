package io.stargazer.urlshortener.service.category;

import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.helper.BaseEntityHelper;
import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.model.entity.Category;
import io.stargazer.urlshortener.model.entity.User;
import io.stargazer.urlshortener.model.request.PostCategoryRequest;
import io.stargazer.urlshortener.model.response.PostCategoryResponse;
import io.stargazer.urlshortener.repository.CategoryRepository;
import io.stargazer.urlshortener.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostCategoryService implements BaseService<PostCategoryRequest, PostCategoryResponse> {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostCategoryService(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostCategoryResponse invoke(PostCategoryRequest input) {
        validateCategory(input);
        User user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND,
                        "4041",
                        "User not found",
                        "User not found")
                );

        Category category = Category.builder()
                .name(input.getCategoryName())
                .description(input.getCategoryDescription())
                .userId(user.getId())
                .build();
        BaseEntityHelper.initBaseEntity(category);
        Category savedCategory = categoryRepository.save(category);

        return PostCategoryResponse.builder()
                .id(savedCategory.getId())
                .build();
    }

    private void validateCategory(PostCategoryRequest input) {
        if (input.getCategoryName().contains(" ")) {
            throw new BaseException(HttpStatus.BAD_REQUEST,
                    "4042",
                    "Category Name Not Valid",
                    "Category name should not contain whitespace, use dash (-) or underscore (_) instead");
        }

        Optional<Category> isCategoryExist = categoryRepository.findByName(input.getCategoryName());
        if (isCategoryExist.isPresent()) {
            throw new BaseException(HttpStatus.CONFLICT,
                    "4092",
                    "Category already exist",
                    "Category Name already exist");
        }
    }
}
