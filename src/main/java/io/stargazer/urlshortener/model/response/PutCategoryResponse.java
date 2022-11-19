package io.stargazer.urlshortener.model.response;

import io.stargazer.urlshortener.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutCategoryResponse extends BaseResponse {

    private String categoryName;
    private String categoryDescription;

}
