package ru.belosludtsev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.belosludtsev.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    public List<Book> index(){
//        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
//    }
//    public Book show(int id){
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
//                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
//    }
//    public void save(Book book){
//        jdbcTemplate.update("INSERT INTO Book(name, author, dateIssue) VALUES(?, ?, ?)",
//                book.getName(), book.getAuthor(), book.getDateIssue());
//    }
//    public void update(int id, Book updateBook){
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, dateIssue=? WHERE id=?",
//                updateBook.getName(), updateBook.getAuthor(), updateBook.getDateIssue(), id);
//    }
//    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//    public void choice(int id, int id_person){
//        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?", id_person, id);
//    }
//    public void choice(int id){
//        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?", null, id);
//    }
}
