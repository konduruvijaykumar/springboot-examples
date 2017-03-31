/**
 * 
 */
package org.pjay.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import org.pjay.entity.SecurityQuestionAnswer;
import org.pjay.entity.UserSecurityInfo;
import org.pjay.model.Results;
import org.pjay.service.UserSecurityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
@RequestMapping("/usersecurityinfo")
public class UserSecurityInfoController {
	
	@Autowired
	UserSecurityInfoService userSecurityInfoService;
	
	@RequestMapping(value="/getall", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getAllSecurityInfo() {
		List<UserSecurityInfo> result = userSecurityInfoService.getAllUserSecurityInfo();
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
		UserSecurityInfo userSecurityInfo = userSecurityInfoService.getByUserName(username);
		if(null != userSecurityInfo){
			return new ResponseEntity<Results>(new Results(userSecurityInfo), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(userSecurityInfo), HttpStatus.NOT_FOUND);
	}
	
	//@RequestMapping(value="/getqnaforauthorization/{username:.+}", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	@RequestMapping(value="/getqnaforauthorization", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> getQNAForAuthorization(@RequestParam("username") String username) {//@PathVariable String username
		UserSecurityInfo userSecurityInfo = userSecurityInfoService.getByUserName(username);
		if(null != userSecurityInfo){
			List<SecurityQuestionAnswer> dataList = new ArrayList<>(userSecurityInfo.getQuestionNAnswers());
			Collections.shuffle(dataList);
			// Try some thing better
			/*
				int iterationLength = dataList.size()-1;
				Random random = new Random();
				int randomNo = 0;
				for (int i = 0; i < iterationLength; i++) {
					randomNo = random.nextInt(dataList.size());
					randomData.add(dataList.get(randomNo));
					dataList.remove(randomNo);
				}
			*/
			dataList.remove((new Random()).nextInt(dataList.size()));
			Set<SecurityQuestionAnswer> randomData = new HashSet<>(dataList);
			userSecurityInfo.setQuestionNAnswers(randomData);
			//userSecurityInfo.getQuestionNAnswers().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			return new ResponseEntity<Results>(new Results(userSecurityInfo), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(userSecurityInfo), HttpStatus.NOT_FOUND);
	}
	
	// http://stackoverflow.com/questions/29481640/unable-to-convert-json-to-java-object-using-spring-boot-rest-service
	// https://www.leveluplunch.com/java/tutorials/014-post-json-to-spring-rest-webservice/
	// https://medium.com/@darilldrems/how-to-send-arrays-with-get-or-post-request-in-postman-f87ca70b154e#.murvo5ky0
	@RequestMapping(value="/isanswerscorrect", method={RequestMethod.POST}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> isAnswersCorrect(@RequestBody UserSecurityInfo userSecurityInfoObj){//@RequestPart UserSecurityInfo userSecurityInfoObj
		UserSecurityInfo userSecurityInfo = userSecurityInfoService.getByUserName(userSecurityInfoObj.getUserName());
		int correctAnswerCount = 0;
		Boolean isCorrectAnswer = Boolean.FALSE;
		if(null != userSecurityInfo && null != userSecurityInfoObj){
			for(SecurityQuestionAnswer securityQuestionAnswer:userSecurityInfoObj.getQuestionNAnswers()){
				isCorrectAnswer = findIfAnswerIsCorrect(securityQuestionAnswer.getQuestion(), securityQuestionAnswer.getAnswer(), userSecurityInfo);
				if(isCorrectAnswer){
					correctAnswerCount = correctAnswerCount+1;
				}
			}
			if(correctAnswerCount >= (userSecurityInfoObj.getQuestionNAnswers().size()-1)){
				return new ResponseEntity<Results>(new Results(Boolean.TRUE), HttpStatus.OK);
			}
			return new ResponseEntity<Results>(new Results(Boolean.FALSE), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(Boolean.FALSE), HttpStatus.NOT_FOUND);
	}
	
	// http://stackoverflow.com/questions/13442678/spring-mvc-how-take-the-parameter-value-of-a-get-http-request-in-my-controller-m
	@RequestMapping(value="/isanswercorrect", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Results> isAnswerCorrect(@RequestParam("username") String username, @RequestParam("question") String question, @RequestParam("answer") String answer){
		//System.out.println("username, question, answer:: \t"+ username + ",\t" + question + ",\t" +answer);
		UserSecurityInfo userSecurityInfo = userSecurityInfoService.getByUserName(username);
		if(null != userSecurityInfo){
			Boolean foundAnswer = findIfAnswerIsCorrect(question, answer, userSecurityInfo);
			return new ResponseEntity<Results>(new Results(foundAnswer), HttpStatus.OK);
		}
		return new ResponseEntity<Results>(new Results(Boolean.FALSE), HttpStatus.NOT_FOUND);
	}

	private Boolean findIfAnswerIsCorrect(String question, String answer, UserSecurityInfo userSecurityInfo) {
		Function<Set<SecurityQuestionAnswer>, Boolean> myFunction = (Set<SecurityQuestionAnswer> sqaSet) -> {
			for (SecurityQuestionAnswer securityQuestionAnswer : sqaSet) {
				if(securityQuestionAnswer.getQuestion().equalsIgnoreCase(question) && securityQuestionAnswer.getAnswer().equalsIgnoreCase(answer))
					return Boolean.TRUE;
			}
			return Boolean.FALSE;
		};
		
		//Boolean foundAnswer = myFunction.apply(userSecurityInfo.getQuestionNAnswers());
		return myFunction.apply(userSecurityInfo.getQuestionNAnswers());
	}
	
}
