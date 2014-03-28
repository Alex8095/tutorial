/**
 * 
 */
package com.frogorf.tutorial.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frogorf.tutorial.domain.Book;
import com.frogorf.tutorial.domain.Tutorial;

/** @author Tsurkin Alex
 * @version */
public interface TutorialService {

	public List<Tutorial> findTutorialAll();

	public Page<Tutorial> findTutorailAllByTutorial(Tutorial tutorial, Pageable pageable);

	public Tutorial findTutorialById(int id);

	public void saveTutorial(Tutorial tutorial);

	public void deleteTutorial(int id);

	public Page<Tutorial> findTutorialAllByPage(Pageable pageable);

	public List<Book> findBookAll();

	public Book findBookById(int id);

	public void saveBook(Book book);

	public void deleteBook(int id);
}
