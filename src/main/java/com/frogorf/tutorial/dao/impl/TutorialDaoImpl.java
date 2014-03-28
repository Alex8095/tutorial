/**
 * 
 */
package com.frogorf.tutorial.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.frogorf.tutorial.dao.TutorialDao;
import com.frogorf.tutorial.domain.Book;
import com.frogorf.tutorial.domain.Tutorial;

/** @author Tsurkin Alex
 * @version */
@Repository("tutorialDao")
public class TutorialDaoImpl implements TutorialDao {

	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * 
	 * @see com.frogorf.isawyou.dao.TutorialDao#findAll() */
	@Override
	public List<Tutorial> findTutorialAll() {
		return sessionFactory.getCurrentSession().createQuery("from Tutorial").list();
	}

	/* @see
	 * com.frogorf.tutorial.dao.TutorialDao#findTutorailAllByTutorial(com.frogorf
	 * .tutorial.domain.Tutorial, java.awt.print.Pageable) */
	@Override
	public Page<Tutorial> findTutorailAllByTutorial(Tutorial tutorial, Pageable pageable) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tutorial.class);
		if (tutorial != null) {
			if (tutorial.getBook() != null)
				criteria.add(Restrictions.eq("book", tutorial.getBook()));
			if (tutorial.getCategory() != null)
				criteria.add(Restrictions.eq("category", tutorial.getCategory()));
			if (tutorial.getSubject() != null)
				criteria.add(Restrictions.like("subject", tutorial.getSubject() + "%"));
			if (tutorial.getDescription() != null) {
				System.out.println("not empty");
				criteria.add(Restrictions.like("description", tutorial.getDescription()));
			}
		}
		return new PageImpl<Tutorial>(criteria.list(), pageable, criteria.list().size());
	}

	/* (non-Javadoc)
	 * 
	 * @see com.frogorf.isawyou.dao.TutorialDao#findById(java.lang.int) */
	@Override
	public Tutorial findTutorialById(int id) {
		return (Tutorial) sessionFactory.getCurrentSession().get(Tutorial.class, id);
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.frogorf.isawyou.dao.TutorialDao#save(com.frogorf.isawyou.domain.
	 * Tutorial) */
	@Override
	public void saveTutorial(Tutorial tutorial) {
		sessionFactory.getCurrentSession().saveOrUpdate(tutorial);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.frogorf.isawyou.dao.TutorialDao#delete(java.lang.int) */
	@Override
	public void deleteTutorial(int id) {
		sessionFactory.getCurrentSession().delete(findTutorialById(id));
	}

	/* @see com.frogorf.tutorial.dao.TutorialDao#findBookAll() */
	@Override
	public List<Book> findBookAll() {
		return sessionFactory.getCurrentSession().createQuery("from Book").list();
	}

	/* @see com.frogorf.tutorial.dao.TutorialDao#findBookById(int) */
	@Override
	public Book findBookById(int id) {
		return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
	}

	/* @see
	 * com.frogorf.tutorial.dao.TutorialDao#saveBook(com.frogorf.tutorial.domain
	 * .Book) */
	@Override
	public void saveBook(Book book) {
		sessionFactory.getCurrentSession().saveOrUpdate(book);
	}

	/* @see com.frogorf.tutorial.dao.TutorialDao#deleteBook(int) */
	@Override
	public void deleteBook(int id) {
		sessionFactory.getCurrentSession().delete(findBookById(id));
	}
}