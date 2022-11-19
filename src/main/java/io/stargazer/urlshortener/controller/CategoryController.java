package io.stargazer.urlshortener.controller;

import io.stargazer.urlshortener.base.BasePageWrapperResponse;
import io.stargazer.urlshortener.base.EmptyResponse;
import io.stargazer.urlshortener.model.request.DeleteCategoryRequest;
import io.stargazer.urlshortener.model.request.GetCategoryListRequest;
import io.stargazer.urlshortener.model.request.PostCategoryRequest;
import io.stargazer.urlshortener.model.request.PutCategoryRequest;
import io.stargazer.urlshortener.model.response.GetCategoryResponse;
import io.stargazer.urlshortener.model.response.PostCategoryResponse;
import io.stargazer.urlshortener.model.response.PutCategoryResponse;
import io.stargazer.urlshortener.service.category.DeleteCategoryService;
import io.stargazer.urlshortener.service.category.GetListCategoryService;
import io.stargazer.urlshortener.service.category.PostCategoryService;
import io.stargazer.urlshortener.service.category.PutCategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortener/v1")
public class CategoryController {

    private final PostCategoryService postCategoryService;
    private final PutCategoryService putCategoryService;
    private final GetListCategoryService getListCategoryService;
    private final DeleteCategoryService deleteCategoryService;

    public CategoryController(PostCategoryService postCategoryService,
                              PutCategoryService putCategoryService,
                              GetListCategoryService getListCategoryService,
                              DeleteCategoryService deleteCategoryService) {
        this.postCategoryService = postCategoryService;
        this.putCategoryService = putCategoryService;
        this.getListCategoryService = getListCategoryService;
        this.deleteCategoryService = deleteCategoryService;
    }

    @PostMapping("/category")
    public PostCategoryResponse createCategory(@RequestBody PostCategoryRequest request) {
        return postCategoryService.invoke(request);
    }

    @PutMapping("/category")
    public PutCategoryResponse editCategory(@RequestBody PutCategoryRequest request) {
        return putCategoryService.invoke(request);
    }

    @GetMapping("/category-list")
    public BasePageWrapperResponse<GetCategoryResponse> getListCategory(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @RequestParam String userId) {
        return getListCategoryService.invoke(GetCategoryListRequest.builder()
                .page(page)
                .size(size)
                .userId(userId)
                .build()
        );
    }

    @DeleteMapping("/category/{categoryId}")
    public EmptyResponse softDeleteCategory(@PathVariable("categoryId") String categoryId) {
        return deleteCategoryService.invoke(DeleteCategoryRequest.builder()
                .id(categoryId)
                .build()
        );
    }



}
