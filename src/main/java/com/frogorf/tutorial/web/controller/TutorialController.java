package com.frogorf.tutorial.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.frogorf.dictionary.service.DictionaryService;
import com.frogorf.tutorial.domain.Tutorial;
import com.frogorf.tutorial.service.TutorialService;
import com.frogorf.web.utils.Grid;

/** Handles requests for the application home page. */
@Controller
@SessionAttributes(types = Tutorial.class)
public class TutorialController {

	private static final Logger logger = LoggerFactory.getLogger(TutorialController.class);

	@Autowired
	private TutorialService tutorialService;

	@Autowired
	private DictionaryService dictionaryService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/tutorials", method = RequestMethod.GET)
	public String processFindForm(Model model) {
		List<Tutorial> tutorials = this.tutorialService.findTutorialAll();
		model.addAttribute("tutorials", tutorials);
		return "tutorial/list";
	}

	@RequestMapping(value = "/tutorials/grid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Grid<Tutorial> listGrid(@RequestParam(value = "page", required = false) Integer page) {
		Page<Tutorial> tutorialPage = tutorialService.findTutorailAllByTutorial(null, null);
		Grid<Tutorial> grid = new Grid<Tutorial>();
		grid.setCurrentPage(tutorialPage.getNumber() + 1);
		grid.setTotalPages(tutorialPage.getTotalPages());
		grid.setTotalRecords(tutorialPage.getTotalElements());
		grid.setData(tutorialPage.getContent());
		return grid;
	}

	@RequestMapping(value = "/tutorial/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		Tutorial tutorial = new Tutorial();
		model.addAttribute(tutorial);
		model.addAttribute("dictionaryValues", dictionaryService.findDictionaryValueAll());
		model.addAttribute("books", tutorialService.findBookAll());
		return "tutorial/form";
	}

	@RequestMapping(value = "/tutorial/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Tutorial tutorial, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "tutorial/form";
		} else {
			this.tutorialService.saveTutorial(tutorial);
			status.setComplete();
			return "redirect:/tutorial/" + tutorial.getId();
		}
	}

	@RequestMapping(value = "/tutorial/edit/{tutorialId}", method = RequestMethod.GET)
	public String initUpdateTutorialForm(@PathVariable("tutorialId") int tutorialId, Model model) {
		model.addAttribute("tutorial", this.tutorialService.findTutorialById(tutorialId));
		model.addAttribute("dictionaryValues", dictionaryService.findDictionaryValueAll());
		model.addAttribute("books", tutorialService.findBookAll());
		return "tutorial/form";
	}

	@RequestMapping(value = "/tutorial/edit/{tutorialId}", method = RequestMethod.POST)
	public String processUpdateTutorialForm(Tutorial tutorial, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "tutorial/form";
		} else {
			this.tutorialService.saveTutorial(tutorial);
			status.setComplete();
			return "redirect:/tutorial/{tutorialId}";
		}
	}

	/** Custom handler for displaying an Tutorial.
	 * 
	 * @param TutorialId the ID of the Tutorial to display
	 * @return a ModelMap with the model attributes for the view */
	@RequestMapping("/tutorial/{tutorialId}")
	public String showTutorial(@PathVariable("tutorialId") int tutorialId, Model model) {
		Tutorial tutorial = this.tutorialService.findTutorialById(tutorialId);
		model.addAttribute("tutorial", tutorial);
		return "tutorial/details";
	}

	@RequestMapping(value = "/tutorial/json/{tutorialId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Tutorial showTutorialJson(@PathVariable("tutorialId") int tutorialId) {
		Tutorial tutorial = this.tutorialService.findTutorialById(tutorialId);
		tutorial.setSubject("subject");
		return tutorial;
	}

	@RequestMapping(value = "/tutorial/delete/{tutorialId}")
	public String deleteTutorial(@PathVariable("tutorialId") int tutorialId, Model model) {
		this.tutorialService.deleteTutorial(tutorialId);
		return "redirect:/tutorials";
	}
}
