package ru.belosludtsev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belosludtsev.springcourse.models.Book;
import ru.belosludtsev.springcourse.models.Person;
import ru.belosludtsev.springcourse.repositories.BooksRepositories;
import ru.belosludtsev.springcourse.repositories.PeopleRepositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepositories booksRepositories;

    @Autowired
    public BooksService(BooksRepositories booksRepositories, PeopleRepositories peopleRepositories, PeopleRepositories peopleRepositories1) {
        this.booksRepositories = booksRepositories;
    }
    public List<Book> findAll() {
        return booksRepositories.findAll();
    }
    public Page<Book> findAll(Pageable pageable){
        return booksRepositories.findAll(pageable);
    }
    public List<Book> findAll(Sort var1){
        return booksRepositories.findAll(var1);
    }
    public List<Book> findByOwner(Person owner){
        return booksRepositories.findByOwner(owner);
    }
    public Book findOne(int id) {
        Optional<Book> bookOptional = booksRepositories.findById(id);
        return bookOptional.orElse(null);
    }
    @Transactional
    public void save(Book book){
        booksRepositories.save(book);
    }
    @Transactional
    public void update(int id, Book updateBook){
        updateBook.setId(id);
        booksRepositories.save(updateBook);
    }
    @Transactional
    public void delete(int id){
        booksRepositories.deleteById(id);
    }
    @Transactional
    public void choice(Book book, Person person){
        book.setOwner(person);
        booksRepositories.save(book);
    }
    @Transactional
    public void cancelChoice(Book book){
        book.setOwnerNull(book.getOwner());
        booksRepositories.save(book);
    }
    public List<Book> search(String inputText){
        return booksRepositories.findByNameStartingWith(inputText);
    }
}
