package com.studygroupzs.virtuallibrary.service;

import org.springframework.stereotype.Service;

import com.studygroupzs.virtuallibrary.apiresponse.GoogleBooksApiResponse;
import com.studygroupzs.virtuallibrary.client.GoogleBooksClient;

@Service
public class BookService {

	    private final GoogleBooksClient googleBooksClient;
	    
	    public BookService(GoogleBooksClient googleBooksClient) {
	        this.googleBooksClient = googleBooksClient;
	    }

	    public GoogleBooksApiResponse findBooks(String query) {
	        return googleBooksClient.getBookInfoByIsbn(query);
	    
	    }

}
