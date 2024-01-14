package ru.belosludtsev.springcourse.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.springcourse.models.Book;
import ru.belosludtsev.springcourse.models.Person;
import ru.belosludtsev.springcourse.services.BooksService;
import ru.belosludtsev.springcourse.services.PeopleService;
import ru.belosludtsev.springcourse.utils.BookValidator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookValidator bookValidator;

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookValidator bookValidator, BooksService booksService, PeopleService peopleService) {
        this.bookValidator = bookValidator;
        this.booksService = booksService;
        this.peopleService = peopleService;
    }
    @GetMapping
    public String index(Model model, @RequestParam(value="books", required = false) Integer number_page,
                        @RequestParam(value="books_per_page", required = false) Integer value_page,
                        @RequestParam(value="sort_by_year", required = false) Boolean flag){
        if (number_page != null && value_page != null){
            if (flag != null && flag == true){
                model.addAttribute("books", booksService.findAll(PageRequest.of(number_page, value_page, Sort.by("dateIssue"))));
            }
            else {
                model.addAttribute("books", booksService.findAll(PageRequest.of(number_page, value_page)));
            }
        }
        else{
            if (flag != null && flag == true){
                model.addAttribute("books", booksService.findAll(Sort.by("dateIssue")));
            }
            else {
                model.addAttribute("books", booksService.findAll());;
            }
        }
        return "book/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("persons", peopleService.findAll());
        model.addAttribute("person2", booksService.findOne(id).getOwner());
        return "book/show";
    }
    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("book", new Book());
        return "book/new";
    }
    @PostMapping
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors()){
            return "/book/new";
        }
        booksService.save(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.findOne(id));
        return "book/update";
    }
    @PatchMapping("/{id}")
    public String saveUpdate(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") int id){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()){
            return "book/update";
        }
        booksService.update(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("{id}/choice")
    public String choice(@PathVariable("id") int id,@ModelAttribute("person") Person person){
        booksService.choice(booksService.findOne(id), person);
        return "redirect:/book";
    }
    @PatchMapping("{id}/notChoice")
    public String notChoice(@PathVariable("id") int id){
        booksService.cancelChoice(booksService.findOne(id));
        return "redirect:/book";
    }
    @GetMapping("/search")
    public String search(){
        return "book/search";
    }
    @PostMapping("/search")
    public String search(@RequestParam(name="input_text", required = false) String text, Model model){
        model.addAttribute("books", booksService.search(text));
        return "book/search";
    }
}
