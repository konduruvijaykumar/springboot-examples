/**
 * 
 */
package org.pjay.controller;

import java.util.List;

import org.pjay.entity.UserTokenInfo;
import org.pjay.model.Results;
import org.pjay.service.UserTokenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
@RequestMapping("/usertokeninfo")
public class UserTokenInfoController {
	
	@Autowired
	UserTokenInfoService userTokenInfoService;
	
	@RequestMapping(value="/getall", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUserTokenInfo() {
		List<UserTokenInfo> result = userTokenInfoService.getAllUserTokenInfo();
		if(null != result){
			return new ResponseEntity<Results>(new Results(result), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(result), HttpStatus.NOT_FOUND);
	}
	
	// IMP: Temporary fix
	// http://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated
	// http://stackoverflow.com/questions/3526523/spring-mvc-pathvariable-getting-truncated
	
	//@RequestMapping(value="/getbyusername/{username:.+}", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	@RequestMapping(value="/getbyusername", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getByUserName(@RequestParam("username") String username) {//@PathVariable String username
		UserTokenInfo userTokenInfo = userTokenInfoService.getByUserName(username);
		if(null != userTokenInfo){
			return new ResponseEntity<Results>(new Results(userTokenInfo), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(userTokenInfo), HttpStatus.NOT_FOUND);
	}

}
