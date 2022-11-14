package io.stargazer.urlshortener.controller;

import io.stargazer.urlshortener.model.request.PostCategoryRequest;
import io.stargazer.urlshortener.model.response.PostCategoryResponse;
import io.stargazer.urlshortener.service.category.PostCategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortener/v1")
public class CategoryController {

    private final PostCategoryService postCategoryService;

    public CategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    @PostMapping("/category")
    public PostCategoryResponse createCategory(@RequestBody PostCategoryRequest request) {
        return postCategoryService.invoke(request);
    }

}
