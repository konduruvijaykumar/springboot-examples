/**
 * 
 */
package org.pjay.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pjay.entity.UserTokenInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */

// http://www.adeveloperdiary.com/java/spring-boot/create-restful-webservices-using-spring-boot/

// http://stackoverflow.com/questions/7649259/how-to-set-to-default-to-json-instead-of-xml-in-jersey
@RestController
@RequestMapping("/userinfo")
//@EnableWebMvc//Not required
public class UserInfoController {
	
	/*
		public Response abc() {
			return Response.status(Status.OK).entity(Arrays.asList(new String[]{"",""})).build();
		}
	*/
	/*@RequestMapping(value="/testapp",
			method={RequestMethod.GET},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})*/
	
	/*
		@RequestMapping(method={RequestMethod.GET},
				produces={MediaType.APPLICATION_JSON_VALUE})
		public @ResponseBody ResponseEntity<Collection<String>> testingApp(){
			System.out.println("testingApp()");
			return new ResponseEntity<Collection<String>>(Arrays.asList(new String[]{"Vijay","Konduru"}), HttpStatus.OK);//Arrays.asList(new String[]{"Vijay","Konduru"});
		}
	*/
	
	// Main reason for 406 error which i received every time is explained in below note. Just add only one producers json will solve most of the issues
	@RequestMapping(method={RequestMethod.GET},
			produces={MediaType.APPLICATION_JSON_VALUE})
	public List<String> testingApp(){
		System.out.println("testingApp()");
		return Arrays.asList(new String[]{"Vijay","Konduru"});
	}
	
	@RequestMapping(value="/testapp1", method={RequestMethod.GET},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserTokenInfo testingApp1() {
		System.out.println("testingApp1()");
		return new UserTokenInfo(2L,"vkonduru","AXPUSXABCD");
	}
	
	// This does not work really well
	// Note: For many of try's earlier with produces having both xml and json i used to get.
	// There was an unexpected error (type=Not Acceptable, status=406). Could not find acceptable representation
	// This is due to object returned was generic List, This can be corrected if at least on annotation like @XmlRootElement to marshal into xml object.
	// As we cannot change in library classes we return only json output and with our model objects we can convert them into xml by adding above xml binding annotation
	/*
		@RequestMapping(value="/testapp2", method={RequestMethod.GET},
				consumes={MediaType.ALL_VALUE},
				produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
		public GenericEntity<List<String>> testingApp2() {
			System.out.println("testingApp2()");
			//Arrays.asList(new String[]{"Vjay","Pjay"});
			List<String> nameList = Arrays.asList(new String[]{"Vjay","Pjay"});
			return new GenericEntity<List<String>>(nameList){};
		}
	*/
	
	@RequestMapping(value="/testapp3", method={RequestMethod.GET},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Map<String, String> testingApp3() {
		System.out.println("testingApp3()");
		HashMap<String, String> tempMap = new HashMap<>();
		tempMap.put("Vijay", "Vjay");
		tempMap.put("Puranjay", "Pjay");
		return tempMap;
	}
	
/*	@RequestMapping(value="/testapp4", method={RequestMethod.GET},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})*/
	@RequestMapping(value="/testapp4", method={RequestMethod.GET},
			produces={MediaType.APPLICATION_JSON_VALUE})
	public Map<String, UserTokenInfo> testingApp4() {
		System.out.println("testingApp4()");
		HashMap<String, UserTokenInfo> tempMap = new HashMap<>();
		tempMap.put("Vijay", new UserTokenInfo(2L,"Vjay","AXPUSXABCD"));
		tempMap.put("Puranjay", new UserTokenInfo(3L,"Pjay","AXPUSXDCBA"));
		return tempMap;
	}
	
	@RequestMapping(value="/testapp5", method={RequestMethod.GET},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Set<String> testingApp5() {
		System.out.println("testingApp5()");
		Set<String> tempMap = new HashSet<>();
		tempMap.add("Vjay");
		tempMap.add("Pjay");
		return tempMap;
	}
	
	@RequestMapping(value="/testapp6", method={RequestMethod.GET},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Set<UserTokenInfo> testingApp6() {
		System.out.println("testingApp6()");
		Set<UserTokenInfo> tempMap = new HashSet<>();
		tempMap.add(new UserTokenInfo(2L,"Vjay","AXPUSXABCD"));
		tempMap.add(new UserTokenInfo(3L,"Pjay","AXPUSXDCBA"));
		return tempMap;
	}

}
