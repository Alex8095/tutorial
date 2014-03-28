package com.frogorf.tutorial.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.frogorf.tutorial.domain.Book;
import com.frogorf.tutorial.domain.Tutorial;

/** <p>
 * Integration test using the 'Spring Data' profile.
 * 
 * @see AbstractisawyouServiceTests AbstractisawyouServiceTests for more
 *      details.
 *      </p>
 * @author Tsurkin Alex */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/spring/business-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
public class TutorialServiceTest {

	@Autowired
	protected TutorialService tutorialService;

	private static final Logger logger = LoggerFactory.getLogger(TutorialServiceTest.class);

	@Test
	@Transactional
	public void findTutorailAllByTutorial() {
		Sort sort = new Sort(Sort.Direction.DESC, "subject");
		PageRequest pageRequest = new PageRequest(1, 10, sort);
		Tutorial tutorial = new Tutorial();
		tutorial.setSubject("boxing");
		Page<Tutorial> tutorials = this.tutorialService.findTutorailAllByTutorial(tutorial, pageRequest);
		assertEquals(tutorials.getContent().size(), 1);
		assertEquals(tutorials.getTotalPages(), 1);
	}

	@Test
	@Transactional
	public void insertTutorial() {
		List<Tutorial> tutorials = this.tutorialService.findTutorialAll();
		int found = tutorials.size();
		Tutorial tutorial = new Tutorial();
		tutorial.setDescription("description");
		tutorial.setSubject("subject");
		tutorial.setSummary("summary");
		this.tutorialService.saveTutorial(tutorial);
		tutorials = this.tutorialService.findTutorialAll();
		assertEquals("Verifying number of  after inserting a new one.", found + 1, tutorials.size());
	}

	@Test
	@Transactional
	public void updateTutorial() throws Exception {
		Tutorial t1 = this.tutorialService.findTutorialById(1);
		String old = t1.getSubject();
		t1.setSubject(old + "X");
		this.tutorialService.saveTutorial(t1);
		t1 = this.tutorialService.findTutorialById(1);
		assertEquals(old + "X", t1.getSubject());
	}

	@Test
	@Transactional
	public void deleteTutorial() {
		tutorialService.deleteTutorial(1);
		assertEquals(tutorialService.findTutorialById(1), null);
	}

	@Test
	@Transactional
	public void insertBook() {
		List<Book> books = this.tutorialService.findBookAll();
		int found = books.size();
		Book book = new Book();
		book.setName("name");
		book.setAuthors("authors");
		this.tutorialService.saveBook(book);
		books = this.tutorialService.findBookAll();
		assertEquals("Verifying number of  after inserting a new one.", found + 1, books.size());
	}

	@Test
	public void findBook() {
		// Collection<PetType> types = this.tutorialService.findPetTypes();
		// Pet pet7 = this.clinicService.findPetById(7);
		// assertTrue(pet7.getName().startsWith("Samantha"));
		// assertEquals(EntityUtils.getById(types, PetType.class, 1).getId(),
		// pet7.getType().getId());
		// assertEquals("Jean", pet7.getOwner().getFirstName());
		// Pet pet6 = this.clinicService.findPetById(6);
		// assertEquals("George", pet6.getName());
		// assertEquals(EntityUtils.getById(types, PetType.class, 4).getId(),
		// pet6.getType().getId());
		// assertEquals("Peter", pet6.getOwner().getFirstName());
	}

	@Test
	@Transactional
	public void updateBook() throws Exception {
		Book b1 = this.tutorialService.findBookById(1);
		String old = b1.getName();
		b1.setName(old + "X");
		this.tutorialService.saveBook(b1);
		b1 = this.tutorialService.findBookById(1);
		assertEquals(old + "X", b1.getName());
	}

	@Test
	@Transactional
	public void deleteBook() {
		tutorialService.deleteBook(1);
		assertEquals(tutorialService.findBookById(1), null);
	}
}