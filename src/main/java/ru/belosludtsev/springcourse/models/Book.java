package ru.belosludtsev.springcourse.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="Book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min=1, max=100, message = "Название составляте от 1 до 100 символов")
    private String name;
    @NotEmpty(message = "У нас тут не русские народные сказки")
    @Column(name="author")
    private String author;
    @Column(name="dateIssue")
    @Min(value = 1200, message = "Ты где достал такого мамонта")
    private int dateIssue;

    @ManyToOne
    @JoinColumn(name="id_person", referencedColumnName ="id")
    private Person owner;
    @Column(name="dateOfTake")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTake;

    @Transient
    private boolean flag;

    public Date getDateOfTake() {
        return dateOfTake;
    }

    public void setDateOfTake(Date dateOfTake) {
        this.dateOfTake = dateOfTake;
    }

    public boolean getFlag() {
        Date currentDate = new Date();
        Date dateOfTakeValue = getDateOfTake();
        long differenceInMills = currentDate.getTime() - dateOfTakeValue.getTime();
        long differenceInDays = differenceInMills / (24 * 60 * 60 * 1000);
        flag = differenceInDays > 10;
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Book(int id, String name, String author, int dateIssue, int id_person, Person owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.dateIssue = dateIssue;
        this.owner = owner;
    }
    public Book(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(int dateIssue) {
        this.dateIssue = dateIssue;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        if(owner.getBooks() == null){
            owner.setBooks(new ArrayList<>());
        }
        owner.getBooks().add(this);
        this.owner = owner;
    }
    public void setOwnerNull(Person owner) {
        owner.getBooks().remove(this);
        this.owner = null;
    }
}
