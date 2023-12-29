package com.studygroupzs.virtuallibrary.apiresponse;

import java.util.List;

import lombok.Data;

@Data
public class GoogleBooksApiResponse {
	private List<GoogleBookItem> items; // encapsula a lista de infos de cada volume
}
