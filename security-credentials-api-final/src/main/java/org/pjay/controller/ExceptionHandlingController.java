/**
 * 
 */
package org.pjay.controller;

import javax.servlet.http.HttpServletRequest;

import org.pjay.exceptions.UserDefinedException;
import org.pjay.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author vijayk
 *
 */
// http://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-error-handling-custom-error-pages
// https://www.youtube.com/watch?v=Mmh78Yf_6AM
// https://www.youtube.com/watch?v=N2afx4KljvU
// IMP: below url for solving 404 error page when opening links on browser
// http://stackoverflow.com/questions/36733254/spring-boot-rest-how-to-configure-404-resource-not-found
// https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
// TODO: IMP: /error by default configured by spring will cause error stack trace, we have to override its behavior

//@ControllerAdvice
//@ControllerAdvice(basePackageClasses={UserInfoController.class,UserTokenInfoController.class})
//@ControllerAdvice(basePackages={"org.pjay.controller"})
//@RestControllerAdvice(basePackageClasses={UserInfoController.class,UserTokenInfoController.class})
//@RestControllerAdvice(basePackages={"org.pjay.controller"})
@RestControllerAdvice
public class ExceptionHandlingController {
	
	@Autowired
	private Error error;
	
	//@ExceptionHandler(Exception.class)
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<Error> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		error.setStatus(status.value());
		error.setErrorMessage(ex.getMessage());
		//return new ResponseEntity<>(new Error(status.value(), ex.getMessage()), status);
		return new ResponseEntity<>(error, status);
	}
	
	//@ExceptionHandler(UserDefinedException.class)
	@ExceptionHandler(value = {UserDefinedException.class})
	public ResponseEntity<Error> handleCustomException(UserDefinedException exception){
		//error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		//error.setErrorMessage(exception.getMessage());
		//return new ResponseEntity<Error>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
