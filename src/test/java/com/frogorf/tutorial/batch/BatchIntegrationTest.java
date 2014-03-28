/**
 * 
 */
package com.frogorf.tutorial.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** @author Tsurkin Alex
 * @version */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/spring/batch/app-context.xml" })
public class BatchIntegrationTest {

	@Test
	public void main() {
		while (true) {

		}
	}
}
