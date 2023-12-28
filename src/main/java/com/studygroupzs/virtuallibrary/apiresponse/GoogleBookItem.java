package com.studygroupzs.virtuallibrary.apiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GoogleBookItem {
		
	@JsonProperty("volumeInfo")
	 private GoogleBookInfo volumeInfo;
}
