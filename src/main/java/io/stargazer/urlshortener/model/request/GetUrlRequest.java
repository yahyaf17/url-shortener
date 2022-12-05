package io.stargazer.urlshortener.model.request;

import io.stargazer.urlshortener.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUrlRequest extends BaseRequest {

    private String urlShort;

}
