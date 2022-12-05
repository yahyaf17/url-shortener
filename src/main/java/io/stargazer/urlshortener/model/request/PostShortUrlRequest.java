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
public class PostShortUrlRequest extends BaseRequest {

    @NotNull(message = "Original url must not be null")
    private String originalUrl;
    @NotNull(message = "Shorted url must not be null")
    private String shortedUrl;
    private String userId;
    private String categoryName;
    private String categoryId;

}
