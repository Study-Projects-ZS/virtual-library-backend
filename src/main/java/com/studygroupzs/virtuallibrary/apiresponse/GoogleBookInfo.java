package com.studygroupzs.virtuallibrary.apiresponse;

import lombok.Data;

@Data
public class GoogleBookInfo {
	private String description;
    private Double averageRating;
    private GoogleBookImageLinks imageLinks;
    private Integer publishedDate; // Ano de publicação
}
