package ru.belosludtsev.springcourse.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.springcourse.models.Book;
import ru.belosludtsev.springcourse.models.Person;

import java.util.List;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
    Page<Book> findAll(Pageable pageable);
    List<Book> findAll(Sort var1);
    List<Book> findByNameStartingWith(String prefix);
    List<Book> findByOwner(Person owner);
}
