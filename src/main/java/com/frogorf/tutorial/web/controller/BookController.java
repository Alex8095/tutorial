package com.frogorf.tutorial.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.frogorf.tutorial.domain.Book;
import com.frogorf.tutorial.service.TutorialService;

/** Handles requests for the application home page. */
@Controller
@SessionAttributes(types = Book.class)
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private TutorialService tutorialService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String processFindForm(Model model) {
		List<Book> books = this.tutorialService.findBookAll();
		model.addAttribute("books", books);
		return "book/list";
	}

	@RequestMapping(value = "/book/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		Book book = new Book();
		model.addAttribute(book);
		return "book/form";
	}

	@RequestMapping(value = "/book/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Book book, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "book/form";
		} else {
			this.tutorialService.saveBook(book);
			status.setComplete();
			return "redirect:/book/" + book.getId();
		}
	}

	@RequestMapping(value = "/book/edit/{bookId}", method = RequestMethod.GET)
	public String initUpdateBookForm(@PathVariable("bookId") int bookId, Model model) {
		model.addAttribute("book", this.tutorialService.findBookById(bookId));
		return "book/form";
	}

	@RequestMapping(value = "/book/edit/{bookId}", method = RequestMethod.POST)
	public String processUpdateBookForm(Book book, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "book/form";
		} else {
			this.tutorialService.saveBook(book);
			status.setComplete();
			return "redirect:/book/{bookId}";
		}
	}

	/** Custom handler for displaying an Book.
	 * 
	 * @param bookId the ID of the Book to display
	 * @return a ModelMap with the model attributes for the view */
	@RequestMapping("/book/{bookId}")
	public String showBook(@PathVariable("bookId") int bookId, Model model) {
		Book book = this.tutorialService.findBookById(bookId);
		model.addAttribute("book", book);
		return "book/details";
	}

	@RequestMapping(value = "/book/delete/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId, Model model) {
		this.tutorialService.deleteBook(bookId);
		return "redirect:/books";
	}
}
