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
public class PutCategoryRequest extends BaseRequest {

    @NotNull(message = "category id cannot be null")
    private String id;
    private String categoryName;
    private String categoryDescription;

}
