package io.stargazer.urlshortener.model.response;

import io.stargazer.urlshortener.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCategoryResponse extends BaseResponse {

    private String id;
    private String name;
    private String description;
    private Boolean isDeleted;
    private Timestamp updatedAt;
    private Timestamp createdAt;

}
