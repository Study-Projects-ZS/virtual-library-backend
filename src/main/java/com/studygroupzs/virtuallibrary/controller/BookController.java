package com.studygroupzs.virtuallibrary.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.studygroupzs.virtuallibrary.apiresponse.*;
import com.studygroupzs.virtuallibrary.client.GoogleBooksClient;
import com.studygroupzs.virtuallibrary.model.Book;
import com.studygroupzs.virtuallibrary.repository.BookRepository;
import com.studygroupzs.virtuallibrary.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GoogleBooksClient googleBooksClient;

	@GetMapping("/search")
	public ResponseEntity<GoogleBooksApiResponse> searchBooks(@RequestParam("query") String query) {
		// Here we are calling BookService to fetch information from Google Books.
		GoogleBooksApiResponse response = bookService.findBooks(query);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		return ResponseEntity.ok(bookRepository.findAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		return bookRepository.findById(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID: " + id + " not found!"));
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<Book>> getByTitle(@PathVariable String title) {
		return ResponseEntity.ok(bookRepository.findAllByTitleContainingIgnoreCase(title));
	}

	@GetMapping("/author/{author}")
	public ResponseEntity<List<Book>> getByAuthor(@PathVariable String author) {
		return ResponseEntity.ok(bookRepository.findAllByTitleContainingIgnoreCase(author));
	}

	@PostMapping("/create")
	public ResponseEntity<Book> postBook(@Valid @RequestBody Book book) {
		Book savedBook = bookRepository.save(book);

		if (savedBook != null) {
			enrichBookInformation(savedBook);
			savedBook = bookRepository.save(savedBook);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}

	private void enrichBookInformation(Book book) {
		// Realiza a chamada à API do Google Books usando o Feign Client
		GoogleBooksApiResponse response = googleBooksClient.getBookInfoByIsbn(book.getIsbn());

		// Checa se o isbn retornou uma lista GoogleBookItem válida
		if (response != null && !response.getItems().isEmpty()) {
			// Obtém o primeiro objeto GoogleBookInfo da lista de items na resposta da API
			// do Google Books
			GoogleBookInfo bookInfo = response.getItems().get(0).getVolumeInfo();

			// Extrai da resposta da API as informações adicionais do livro e atualiza o
			// registro do livro salvo anteriormente
			book.setDescription(bookInfo.getDescription());
			book.setCover(getCoverUrl(bookInfo));
			book.setYearOfPublication(bookInfo.getPublishedDate());

			// Tenta converter e definir o rating para BigDecimal
			try {
				Double rating = bookInfo.getAverageRating();
				if (rating != null) {
					book.setRating(BigDecimal.valueOf(rating));
				}
			} catch (NullPointerException e) {
				System.out.println("Não é possível converter um objeto nulo para BigDecimal");
			}
		}
	}

	// Define a capa do livro (se disponível)
	private String getCoverUrl(GoogleBookInfo bookInfo) {
		return (bookInfo.getImageLinks() != null) ? bookInfo.getImageLinks().getThumbnail() : null;
	}

	@PutMapping("/update")
	public ResponseEntity<Book> putBook(@RequestBody @Valid Book book) {
		return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book));
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with ID " + id + " not found"));

		bookRepository.deleteById(id);
	}

}
