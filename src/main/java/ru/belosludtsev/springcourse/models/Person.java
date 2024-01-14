package ru.belosludtsev.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="fullName")
    @NotEmpty(message = "ФИО не может быть пустым")
    @Size(min=3, max=200, message = "Длина должна составлять от 3 до 100")
    private String fullName;

    @Column(name="dateBirth")
    private int dateBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Book> books;

    public Person(int id, String fullName, int dateBirth, List<Book> books) {
        this.id = id;
        this.fullName = fullName;
        this.dateBirth = dateBirth;
        this.books = books;
    }
    public Person(){};

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
