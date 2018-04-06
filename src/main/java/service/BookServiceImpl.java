package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BookDAO;
import model.Book;
/**
 * @author Nicoleta GHITESCU at 27-Mar-18
 */

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookDAO.findOne(bookId);
    }

    public Book saveBook(BookDTO bookDTO) {
        Book bookToBeSaved = new Book(bookDTO.getTitle(), bookDTO.getAuthor());

        if (bookDAO.findByTitleAndAuthor(bookToBeSaved.getTitle(), bookToBeSaved.getAuthor()) == null) {

            bookDAO.save(bookToBeSaved);
            return bookDAO.findByTitleAndAuthor(bookDTO.getTitle(), bookDTO.getAuthor());

        } else {
            return null;
        }
    }

    public Book updateBook(Long bookId, BookDTO bookDTO) {
        Book bookToBeUpdated = bookDAO.findOne(bookId);

        if (bookToBeUpdated != null) {
            bookToBeUpdated.setAuthor(bookDTO.getAuthor());
            bookToBeUpdated.setTitle(bookDTO.getTitle());

            bookDAO.save(bookToBeUpdated);
            return bookToBeUpdated;
        } else {
            return null;
        }
    }

    public void deleteBookById(Long bookId) {
        bookDAO.delete(bookId);
    }
}