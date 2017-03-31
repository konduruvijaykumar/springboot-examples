/**
 * 
 */
package org.pjay.controller;

import java.util.List;

import org.pjay.entity.UserInfo;
import org.pjay.model.Results;
import org.pjay.service.UserInfoService;
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

// http://www.adeveloperdiary.com/java/spring-boot/create-restful-webservices-using-spring-boot/
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/getall", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUserInfo() {
		List<UserInfo> result = userInfoService.getAllUserInfo();
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
		UserInfo userInfo = userInfoService.getByUserName(username);
		if(null != userInfo){
			return new ResponseEntity<Results>(new Results(userInfo), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(userInfo), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/getbyassetid", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getByAssetId(@RequestParam("assetid") String assetid) {//@PathVariable String assetid
		UserInfo userInfo = userInfoService.getByAssetId(assetid);
		if(null != userInfo){
			return new ResponseEntity<Results>(new Results(userInfo), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(userInfo), HttpStatus.NOT_FOUND);
	}

}
