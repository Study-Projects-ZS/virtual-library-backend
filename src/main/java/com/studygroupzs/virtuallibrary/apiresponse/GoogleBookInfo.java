package com.studygroupzs.virtuallibrary.apiresponse;

import lombok.Data;

@Data
public class GoogleBookInfo {
	private String description;
    private Double averageRating;
    private GoogleBookImageLinks imageLinks;
    private String publishedDate; // Ano de publicação
}
