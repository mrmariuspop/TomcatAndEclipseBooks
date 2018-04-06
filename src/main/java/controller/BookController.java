package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Book;
import service.BookDTO;
import service.BookService;
/**
 * @author Nicoleta GHITESCU at 27-Mar-18
 */

@RestController
@RequestMapping("books")
@Api(value = "BooksControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("getAllBooks")
    @ApiOperation("Gets all the books")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Book.class)})
    public List<Book> getAllBooks() {
        try {
            return bookService.getAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getBookById")
    public Book getBookById(@RequestParam Long bookId) {
        try {
            return bookService.getBookById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("saveBook")
    public Book saveBook(@RequestBody BookDTO bookDTO) {
        try {
            return bookService.saveBook(bookDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateBook")
    public Book updateBook(@RequestParam Long bookId, @RequestBody BookDTO bookDTO) {
        try {
            return bookService.updateBook(bookId, bookDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("deleteBookById")
    public String deleteBookById(Long bookId) {
        try {
            bookService.deleteBookById(bookId);
            return "Book with id = " + bookId + " successful deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}