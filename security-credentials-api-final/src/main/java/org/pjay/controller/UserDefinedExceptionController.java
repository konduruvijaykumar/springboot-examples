/**
 * 
 */
package org.pjay.controller;

import org.pjay.exceptions.UserDefinedException;
import org.pjay.model.Results;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
@RequestMapping("/userexceptions")
public class UserDefinedExceptionController {
	
	@RequestMapping("/exception1")
	public ResponseEntity<Results> getException1() throws UserDefinedException, Exception{
		try {
			int var = 20/0;
		} catch (ArithmeticException e/*Exception e*/) {
			throw new UserDefinedException(e);
		}
		return new ResponseEntity<>(new Results(new Object()), HttpStatus.OK);
	}
	
	@RequestMapping("/exception2")
	public ResponseEntity<Results> getException2() throws UserDefinedException, Exception{
		try {
			int []arr = {1,2,3,4};
			System.out.println(arr[4]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new UserDefinedException(e);
		}
		return new ResponseEntity<>(new Results(new Object()), HttpStatus.OK);
	}
	
	@RequestMapping("/exception3")
	public ResponseEntity<Results> getException3() throws UserDefinedException, Exception{
		throw new Exception("Just throwing an exception");
		//return new ResponseEntity<>(new Results(new Object()), HttpStatus.OK);
	}

}
