/**
 * 
 */
package com.frogorf.tutorial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frogorf.tutorial.dao.TutorialDao;
import com.frogorf.tutorial.domain.Book;
import com.frogorf.tutorial.domain.Tutorial;
import com.frogorf.tutorial.service.TutorialService;

/** @author Tsurkin Alex
 * @version */
@Service("tutorialService")
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private TutorialDao tutoriaDao;

	/* @see com.frogorf.tutorial.service.TutorialService#findTutorialAll() */
	@Override
	@Transactional
	public List<Tutorial> findTutorialAll() {
		return tutoriaDao.findTutorialAll();
	}

	/* @see
	 * com.frogorf.tutorial.service.TutorialService#findTutorailAllByTutorial
	 * (com.frogorf.tutorial.domain.Tutorial,
	 * org.springframework.data.domain.Pageable) */
	@Override
	@Transactional
	public Page<Tutorial> findTutorailAllByTutorial(Tutorial tutorial, Pageable pageable) {
		return tutoriaDao.findTutorailAllByTutorial(tutorial, pageable);
	}

	/* @see com.frogorf.tutorial.service.TutorialService#findTutorialById(int) */
	@Override
	@Transactional
	public Tutorial findTutorialById(int id) {
		return tutoriaDao.findTutorialById(id);
	}

	/* @see
	 * com.frogorf.tutorial.service.TutorialService#saveTutorial(com.frogorf
	 * .tutorial.domain.Tutorial) */
	@Override
	@Transactional
	public void saveTutorial(Tutorial tutorial) {
		tutoriaDao.saveTutorial(tutorial);
	}

	/* @see com.frogorf.tutorial.service.TutorialService#deleteTutorial(int) */
	@Override
	@Transactional
	public void deleteTutorial(int id) {
		tutoriaDao.deleteTutorial(id);
	}

	/* @see
	 * com.frogorf.tutorial.service.TutorialService#findTutorialAllByPage(org
	 * .springframework.data.domain.Pageable) */
	@Override
	@Transactional
	public Page<Tutorial> findTutorialAllByPage(Pageable pageable) {
		return null;
	}

	/* @see com.frogorf.tutorial.service.TutorialService#findBookAll() */
	@Override
	@Transactional
	public List<Book> findBookAll() {
		return tutoriaDao.findBookAll();
	}

	/* @see com.frogorf.tutorial.service.TutorialService#findBookById(int) */
	@Override
	@Transactional
	public Book findBookById(int id) {
		return tutoriaDao.findBookById(id);
	}

	/* @see
	 * com.frogorf.tutorial.service.TutorialService#saveBook(com.frogorf.tutorial
	 * .domain.Book) */
	@Override
	@Transactional
	public void saveBook(Book book) {
		tutoriaDao.saveBook(book);
	}

	/* @see com.frogorf.tutorial.service.TutorialService#deleteBook(int) */
	@Override
	@Transactional
	public void deleteBook(int id) {
		tutoriaDao.deleteBook(id);
	}

}
