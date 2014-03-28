/**
 * 
 */
package com.frogorf.tutorial.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frogorf.tutorial.domain.Book;
import com.frogorf.tutorial.domain.Tutorial;

/** @author Tsurkin Alex
 * @version */
public interface TutorialDao {

	public List<Tutorial> findTutorialAll();

	// public List<Tutorial> findTutorailAllByTutorial(Tutorial tutorial,
	// Pageable pageable);

	public Page<Tutorial> findTutorailAllByTutorial(Tutorial tutorial, Pageable pageable);

	public Tutorial findTutorialById(int id);

	public void saveTutorial(Tutorial tutorial);

	public void deleteTutorial(int id);

	public List<Book> findBookAll();

	// public List<Book> findBookAllByBook(Book book, Pageable pageable);

	public Book findBookById(int id);

	public void saveBook(Book book);

	public void deleteBook(int id);
}