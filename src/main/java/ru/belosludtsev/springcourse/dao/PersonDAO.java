package ru.belosludtsev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.belosludtsev.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO{
//    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//    public Person show(int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
//         new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//    public void save(Person person){
//        jdbcTemplate.update("INSERT INTO Person(fullName, dateBirth) VALUES(?,?)",
//                person.getFullName(), person.getDateBirth());
//    }
//    public void update(int id, Person updatePerson){
//        jdbcTemplate.update("UPDATE Person SET fullName=?, dateBirth=? WHERE id=?",
//                updatePerson.getFullName(), updatePerson.getDateBirth(), id);
//    }
//    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
}
