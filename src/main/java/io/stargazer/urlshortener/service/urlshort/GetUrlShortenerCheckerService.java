package io.stargazer.urlshortener.service.urlshort;

import io.stargazer.urlshortener.base.BaseService;
import io.stargazer.urlshortener.helper.BaseException;
import io.stargazer.urlshortener.model.request.GetUrlShortenerCheckerRequest;
import io.stargazer.urlshortener.model.response.GetUrlShorenerCheckerResponse;
import io.stargazer.urlshortener.repository.UrlShortRepository;
import io.stargazer.urlshortener.util.ConflictErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUrlShortenerCheckerService implements BaseService<GetUrlShortenerCheckerRequest, GetUrlShorenerCheckerResponse> {

    private final UrlShortRepository urlShortRepository;

    public GetUrlShortenerCheckerService(UrlShortRepository urlShortRepository) {
        this.urlShortRepository = urlShortRepository;
    }

    @Override
    public GetUrlShorenerCheckerResponse invoke(GetUrlShortenerCheckerRequest input) {
        Optional<String> shortUrlOpt =  urlShortRepository.findByShortUrl(input.getShortUrl());
        if (shortUrlOpt.isPresent()) {
            throw new BaseException(HttpStatus.CONFLICT,
                    ConflictErrorCode.URL_ALREADY_EXIST.getCode(),
                    ConflictErrorCode.URL_ALREADY_EXIST.getTitle(),
                    ConflictErrorCode.URL_ALREADY_EXIST.getMessage()
            );
        }

        return GetUrlShorenerCheckerResponse.builder()
                .isUrlAvailable(true)
                .build();
    }

}
