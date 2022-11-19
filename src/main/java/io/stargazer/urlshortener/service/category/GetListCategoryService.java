package io.stargazer.urlshortener.service.category;

import io.stargazer.urlshortener.base.BasePageWrapperResponse;
import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.model.entity.Category;
import io.stargazer.urlshortener.model.request.GetCategoryListRequest;
import io.stargazer.urlshortener.model.response.GetCategoryResponse;
import io.stargazer.urlshortener.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetListCategoryService implements BaseService<GetCategoryListRequest, BasePageWrapperResponse<GetCategoryResponse>> {

    private final CategoryRepository categoryRepository;

    public GetListCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BasePageWrapperResponse<GetCategoryResponse> invoke(GetCategoryListRequest input) {
        if (0 == input.getPage()) {
            input.setPage(1);
        }
        Pageable pageable = PageRequest.of(input.getPage() - 1, input.getSize());
        Page<Category> categoryPage = categoryRepository.findCategoriesByUserIdPage(pageable, input.getUserId());

        List<GetCategoryResponse> contentList = new ArrayList<>();

        for (Category category : categoryPage.getContent()) {
            contentList.add(GetCategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .isDeleted(category.getDeleted())
                    .updatedAt(category.getUpdatedAt())
                    .createdAt(category.getCreatedAt())
                    .build());
        }

        BasePageWrapperResponse<GetCategoryResponse> response = new BasePageWrapperResponse<>();
        response.setPage(input.getPage());
        response.setSize(input.getSize());
        response.setContent(contentList);
        response.setIsFirstPage(categoryPage.isFirst());
        response.setIsLastPage(categoryPage.isLast());
        response.setTotalContent(categoryPage.getTotalElements());
        response.setTotalPage(categoryPage.getTotalPages());
        return response;
    }
}
