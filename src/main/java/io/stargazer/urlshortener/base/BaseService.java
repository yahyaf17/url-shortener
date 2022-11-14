package io.stargazer.urlshortener.base;

public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
    V invoke(T input);
}
