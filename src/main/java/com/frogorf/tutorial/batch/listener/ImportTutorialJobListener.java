/**
 * 
 */
package com.frogorf.tutorial.batch.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.frogorf.tutorial.domain.Tutorial;
import com.frogorf.tutorial.service.TutorialService;

/** @author Tsurkin Alex
 * @version */
public class ImportTutorialJobListener implements JobExecutionListener {

	private static final Logger logger = LoggerFactory.getLogger(ImportTutorialJobListener.class);

	@Autowired
	private TutorialService tutorialService;

	/* @see org.springframework.batch.core.JobExecutionListener#afterJob(org.
	 * springframework.batch.core.JobExecution) */
	@Override
	public void afterJob(JobExecution arg0) {
		List<Tutorial> tutorials = tutorialService.findTutorialAll();
		for (Tutorial tutorial : tutorials) {
			logger.info("Tutorial :{}", tutorial);
		}
	}

	/* @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.
	 * springframework.batch.core.JobExecution) */
	@Override
	public void beforeJob(JobExecution arg0) {

	}

}
