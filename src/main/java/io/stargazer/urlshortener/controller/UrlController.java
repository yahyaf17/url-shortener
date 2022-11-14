package io.stargazer.urlshortener.controller;

import io.stargazer.urlshortener.model.request.GetUrlShortenerCheckerRequest;
import io.stargazer.urlshortener.model.response.GetUrlShorenerCheckerResponse;
import io.stargazer.urlshortener.service.urlshort.GetUrlShortenerCheckerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortener/v1")
public class UrlController {

    private GetUrlShortenerCheckerService getUrlShortenerCheckerService;

    public UrlController(GetUrlShortenerCheckerService getUrlShortenerCheckerService) {
        this.getUrlShortenerCheckerService = getUrlShortenerCheckerService;
    }

    @GetMapping("/checker/{shortUrl}")
    public GetUrlShorenerCheckerResponse checkIsUrlShortExist(@PathVariable("shortUrl") String shortUrl) {
        return getUrlShortenerCheckerService.invoke(GetUrlShortenerCheckerRequest.builder()
                .shortUrl(shortUrl)
                .build());
    }

}
