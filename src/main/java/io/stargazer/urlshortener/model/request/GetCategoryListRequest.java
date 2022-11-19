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
public class GetCategoryListRequest extends BaseRequest {
    @NotNull(message = "User id must not be null")
    private String userId;
    private Integer page;
    private Integer size;
}
