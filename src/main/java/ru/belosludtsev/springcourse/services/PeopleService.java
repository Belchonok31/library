package ru.belosludtsev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belosludtsev.springcourse.models.Person;
import ru.belosludtsev.springcourse.repositories.PeopleRepositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepositories peopleRepositories;
    @Autowired
    public PeopleService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }
    public List<Person> findAll(){
        return peopleRepositories.findAll();
    }
    public Person findOne(int id){
        Optional<Person> personOptional = peopleRepositories.findById(id);
        return personOptional.orElse(null);
    }
    @Transactional
    public void save(Person person){
        peopleRepositories.save(person);
    }
    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        peopleRepositories.save(updatePerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepositories.deleteById(id);
    }
}
