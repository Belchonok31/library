package ru.belosludtsev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.springcourse.models.Person;
import ru.belosludtsev.springcourse.services.BooksService;
import ru.belosludtsev.springcourse.services.PeopleService;
import ru.belosludtsev.springcourse.utils.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController{
    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public PersonController(PersonValidator personValidator, PeopleService peopleService, BooksService booksService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("persons", peopleService.findAll());
        return "person/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", booksService.findByOwner(peopleService.findOne(id)));
        return "person/show";
    }
    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("person", new Person());
        return "person/new";
    }
    @PostMapping
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/new";
        }
        peopleService.save(person);
        return "redirect:/person";
    }
    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "person/update";
    }
    @PatchMapping("/{id}")
    public String saveUpdate(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                             @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "person/update";
        }
        peopleService.update(id, person);
        return "redirect:/person";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/person";
    }
}
