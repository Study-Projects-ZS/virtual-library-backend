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
		// Aqui estamos chamando o BookService para buscar informações do Google Books.
		GoogleBooksApiResponse response = bookService.findBooks(query);

		// Agora podemos usar essas informações para mostrar ao usuário!
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		return ResponseEntity.ok(bookRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		return bookRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build()); // http status not found
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
	public ResponseEntity<Book> registerBook(@Valid @RequestBody Book book) {
		Book savedBook = bookRepository.save(book);

		// Realiza a chamada à API do Google Books usando o Feign Client
		GoogleBooksApiResponse response = googleBooksClient.getBookInfoByIsbn(book.getIsbn());

		if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
			GoogleBookInfo bookInfo = response.getItems().get(0).getVolumeInfo();

			// Extrai as informações adicionais do livro da resposta da API
			String description = bookInfo.getDescription();
			Double rating = bookInfo.getAverageRating();
			String coverUrl = bookInfo.getImageLinks() != null ? bookInfo.getImageLinks().getThumbnail() : null;
			String yearOfPublication = bookInfo.getPublishedDate(); // Obtendo o ano de publicação

			// Atualiza as informações adicionais do livro no registro salvo anteriormente
			savedBook.setDescription(description);
			try {
				savedBook.setRating(BigDecimal.valueOf(rating)); // Convertendo para BigDecimal
			} catch (NullPointerException e) {
				System.out.println("NullPoint");
			}

			savedBook.setCover(coverUrl);
			savedBook.setYearOfPublication(yearOfPublication); // Definindo o ano de publicação

			// Persiste as informações adicionais no banco de dados
			savedBook = bookRepository.save(savedBook);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}

	@PutMapping("/update")
	public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book) {
		return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book));
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		bookRepository.deleteById(id);
	}

}
