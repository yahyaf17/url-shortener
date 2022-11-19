package io.stargazer.urlshortener.controller;

import io.stargazer.urlshortener.model.request.PostCategoryRequest;
import io.stargazer.urlshortener.model.request.PutCategoryRequest;
import io.stargazer.urlshortener.model.response.PostCategoryResponse;
import io.stargazer.urlshortener.model.response.PutCategoryResponse;
import io.stargazer.urlshortener.service.category.PostCategoryService;
import io.stargazer.urlshortener.service.category.PutCategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortener/v1")
public class CategoryController {

    private final PostCategoryService postCategoryService;
    private final PutCategoryService putCategoryService;

    public CategoryController(PostCategoryService postCategoryService,
                              PutCategoryService putCategoryService) {
        this.postCategoryService = postCategoryService;
        this.putCategoryService = putCategoryService;
    }

    @PostMapping("/category")
    public PostCategoryResponse createCategory(@RequestBody PostCategoryRequest request) {
        return postCategoryService.invoke(request);
    }

    @PutMapping("/category")
    public PutCategoryResponse editCategory(@RequestBody PutCategoryRequest request) {
        return putCategoryService.invoke(request);
    }

}
