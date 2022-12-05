package io.stargazer.urlshortener.controller;

import io.stargazer.urlshortener.model.request.GetUrlRequest;
import io.stargazer.urlshortener.model.request.GetUrlShortenerCheckerRequest;
import io.stargazer.urlshortener.model.request.PostShortUrlRequest;
import io.stargazer.urlshortener.model.response.GetUrlResponse;
import io.stargazer.urlshortener.model.response.GetUrlShorenerCheckerResponse;
import io.stargazer.urlshortener.model.response.PostShortUrlResponse;
import io.stargazer.urlshortener.service.urlshort.GetUrlService;
import io.stargazer.urlshortener.service.urlshort.GetUrlShortenerCheckerService;
import io.stargazer.urlshortener.service.urlshort.PostShortUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortener/v1")
public class UrlController {

    private GetUrlShortenerCheckerService getUrlShortenerCheckerService;
    private PostShortUrlService postShortUrlService;
    private GetUrlService getUrlService;

    public UrlController(GetUrlShortenerCheckerService getUrlShortenerCheckerService, PostShortUrlService postShortUrlService,
                         GetUrlService getUrlService) {
        this.getUrlShortenerCheckerService = getUrlShortenerCheckerService;
        this.postShortUrlService = postShortUrlService;
        this.getUrlService = getUrlService;
    }

    @GetMapping("/checker/{shortUrl}")
    public GetUrlShorenerCheckerResponse checkIsUrlShortExist(@PathVariable("shortUrl") String shortUrl) {
        return getUrlShortenerCheckerService.invoke(GetUrlShortenerCheckerRequest.builder()
                .shortUrl(shortUrl)
                .build());
    }

    @PostMapping("/short-url")
    public PostShortUrlResponse postShortUrl(@RequestBody PostShortUrlRequest request) {
        return postShortUrlService.invoke(request);
    }

    @PostMapping("/{shortUrl}")
    public GetUrlResponse postShortUrl(@PathVariable("shortUrl") String shortUrl) {
        return getUrlService.invoke(GetUrlRequest.builder()
                .urlShort(shortUrl)
                .build());
    }

}
