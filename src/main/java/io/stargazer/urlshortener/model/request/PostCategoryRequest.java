package io.stargazer.urlshortener.model.request;

import io.stargazer.urlshortener.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCategoryRequest extends BaseRequest {
    @NotNull(message = "user id must not null")
    private String userId;
    @NotNull(message = "category name must not null")
    private String categoryName;
    private String categoryDescription;

}
