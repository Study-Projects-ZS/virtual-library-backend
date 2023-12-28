package com.studygroupzs.virtuallibrary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studygroupzs.virtuallibrary.apiresponse.GoogleBooksApiResponse;

@FeignClient(name="googleBooksClient", url="https://www.googleapis.com/books/v1")
public interface GoogleBooksClient {
	
	@GetMapping("/volumes")
    GoogleBooksApiResponse getBookInfoByIsbn(@RequestParam("q") String isbn);
}
