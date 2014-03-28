/**
 * 
 */
package com.frogorf.tutorial.batch;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frogorf.tutorial.domain.Tutorial;
import com.frogorf.tutorial.service.TutorialService;

/** @author Tsurkin Alex
 * @version */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/spring/batch/app-context.xml" })
// @TransactionConfiguration(transactionManager = "transactionManager")
public class TutorialJobTest {

	@Autowired
	TutorialService tutorialService;
	@Autowired
	JobLauncher tutorialJobLauncher;
	@Autowired
	Job tutorialJob;

	@Test
	public void insertTutorial() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		List<Tutorial> tutorials = tutorialService.findTutorialAll();
		int count = tutorials.size() + 1;
		Map<String, JobParameter> jobParamMap = new HashMap<String, JobParameter>();
		jobParamMap.put("inputFile", new JobParameter("D:/tutorials.xml"));
		tutorialJobLauncher.run(tutorialJob, new JobParameters(jobParamMap));
		tutorials = tutorialService.findTutorialAll();
		assertEquals(tutorials.size(), count);
	}
}
