/**
 * 
 */
package org.pjay.controller;

import java.util.ArrayList;
import java.util.List;

import org.pjay.entity.UserTokenInfo;
import org.pjay.model.Results;
import org.pjay.service.UserTokenInfoService;
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
@RequestMapping("/usertokeninfo")
public class UserTokenInfoController {
	
	@Autowired
	DummyData dummyData;
	
	// Testing data or resource available scenario
	@RequestMapping(value="/alltokeninfotest", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUsersTest(){
		//list.add(dummyData.getUserInfo());
		//return new ResponseEntity<List<UserInfo>>(list, HttpStatus.NOT_FOUND);
		List<UserTokenInfo> list = new ArrayList<>();
		list.add(dummyData.getUserTokenInfo());
		return new ResponseEntity<Results>(new Results(list), HttpStatus.OK);
	}
	
	// Testing data or resource not available scenario
	@RequestMapping(value="/alltokeninfotest1", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUsersTest1(){
		return new ResponseEntity<Results>(new Results(), HttpStatus.NOT_FOUND);
	}
	
	/** ========================== All above code is created for testing main code to focus starts from here ============================== */
	
	@Autowired
	UserTokenInfoService userTokenInfoService;
	
	@RequestMapping(value="/getall", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllUserTokenInfo() {
		List<UserTokenInfo> result = userTokenInfoService.getAllUserTokenInfo();
		return new ResponseEntity<Results>(new Results(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getbyusername/{username}", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getByUserName(@PathVariable String username) {
		UserTokenInfo userTokenInfo = userTokenInfoService.getByUserName(username);
		return new ResponseEntity<Results>(new Results(userTokenInfo), HttpStatus.OK);
	}

}
