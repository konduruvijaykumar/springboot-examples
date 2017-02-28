/**
 * 
 */
package org.pjay.controller;

import java.util.ArrayList;
import java.util.List;

import org.pjay.entity.UserSecurityInfo;
import org.pjay.model.Results;
import org.pjay.service.UserSecurityInfoService;
import org.pjay.util.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
@RequestMapping("/usersecurityinfo")
public class UserSecurityInfoController {
	
	@Autowired
	DummyData dummyData;
	
	// Testing data or resource available scenario
	@RequestMapping(value="/allsecurityinfotest", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUsersTest(){
		//list.add(dummyData.getUserInfo());
		//return new ResponseEntity<List<UserInfo>>(list, HttpStatus.NOT_FOUND);
		List<UserSecurityInfo> list = new ArrayList<>();
		list.add(dummyData.getUserSecurityInfo());
		return new ResponseEntity<Results>(new Results(list), HttpStatus.OK);
	}
	
	// Testing data or resource not available scenario
	@RequestMapping(value="/allsecurityinfotest1", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUsersTest1(){
		return new ResponseEntity<Results>(new Results(), HttpStatus.NOT_FOUND);
	}
	
	/** ========================== All above code is created for testing main code to focus starts from here ============================== */
	
	@Autowired
	UserSecurityInfoService userSecurityInfoService;
	
	@RequestMapping(value="/getall", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllSecurityInfo() {
		List<UserSecurityInfo> result = userSecurityInfoService.getAllUserSecurityInfo();
		return new ResponseEntity<Results>(new Results(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getbyusername/{username}", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getByUserName(@PathVariable String username) {
		UserSecurityInfo userSecurityInfo = userSecurityInfoService.getByUserName(username);
		return new ResponseEntity<Results>(new Results(userSecurityInfo), HttpStatus.OK);
	}
}
