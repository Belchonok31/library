package ru.belosludtsev.springcourse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.belosludtsev.springcourse.dao.BookDAO;
import ru.belosludtsev.springcourse.models.Book;
import ru.belosludtsev.springcourse.services.BooksService;

@Component
public class BookValidator implements Validator {
    private final BooksService booksService;
    @Autowired
    public BookValidator(BookDAO bookDAO, BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (book.getDateIssue() == 1200){
            errors.rejectValue("dateIssue", "", "Дата написания >= 1200");
        }
    }
}
