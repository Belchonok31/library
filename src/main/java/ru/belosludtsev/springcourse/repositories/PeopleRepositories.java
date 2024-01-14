package ru.belosludtsev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.springcourse.models.Person;
@Repository
public interface PeopleRepositories extends JpaRepository<Person, Integer> {
}
