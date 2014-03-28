/**
 * 
 */
package com.frogorf.tutorial.batch.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Tsurkin Alex
 * @version */
public class BaenValidationValidator<Tutorial> implements Validator<Tutorial> {

	@Autowired
	private javax.validation.Validation validation;

	/* @see
	 * org.springframework.batch.item.validator.Validator#validate(java.lang
	 * .Object) */
	@Override
	public void validate(Tutorial value) throws ValidationException {
		List<ConstraintViolation<Tutorial>> violations = new ArrayList<ConstraintViolation<Tutorial>>();
	}

}
