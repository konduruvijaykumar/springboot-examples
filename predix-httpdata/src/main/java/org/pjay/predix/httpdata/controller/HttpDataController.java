/**
 * 
 */
package org.pjay.predix.httpdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pjay.predix.httpdata.bean.HttpDataBean;
import org.pjay.predix.httpdata.bean.Result;
import org.pjay.predix.httpdata.bean.SensorData;
import org.pjay.predix.httpdata.entity.HttpData;
import org.pjay.predix.httpdata.service.HttpDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vkonduru3
 *
 */
@RestController
// https://stackoverflow.com/questions/11139571/scope-of-a-spring-controller-and-its-instance-variables
// Looks like controller is also singleton like servlet serving diff requests as multiple threads as "test" value is retaing old request and getting increased request by request
// https://stackoverflow.com/questions/11508405/are-spring-mvc-controllers-singletons
public class HttpDataController {
	
	@Autowired
	HttpDataService httpDataService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	//int test = 1;
	
	@RequestMapping("/v1/welcome")
	public ResponseEntity<String> welcome() {
		//test = test+1;
		return new ResponseEntity<String>("Welcome to HTTP Data Service", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/save", method=RequestMethod.POST)
	public void saveHttpData(HttpDataBean httpDataBean) {
		//test = test+1;
		httpDataService.saveHttpData(httpDataBean);
	}
	
	@RequestMapping(value="/v1/retrieve", method=RequestMethod.GET)
	public ResponseEntity<SensorData> retrieve(@RequestParam String transferId){
		List<HttpData> httpDatas = httpDataService.getHttpData(transferId);
		if(null != httpDatas && httpDatas.size() > 0){
			byte[] data = httpDatas.get(0).getData();
			// https://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
			// https://www.mkyong.com/java/how-do-convert-byte-array-to-string-in-java/
			SensorData sensorData = null;
			try {
				sensorData = objectMapper.readValue(new String(data), SensorData.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(null != sensorData)
				return new ResponseEntity<SensorData>(sensorData, HttpStatus.OK);
			else
				return new ResponseEntity<SensorData>(new SensorData(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SensorData>(new SensorData(), HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/v1/retrieveall")
	public ResponseEntity<List<Result>> retrieve() {
		List<HttpData> httpDatas = httpDataService.getHttpData();
		List<Result> results = new ArrayList<>();
		byte[] data = null;
		if(null != httpDatas && !httpDatas.isEmpty()){
			for (HttpData httpData : httpDatas) {
				SensorData sensorData = null;
				data = httpData.getData();
				try {
					sensorData = objectMapper.readValue(new String(data), SensorData.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(null != sensorData){
					Result result = new Result();
					result.setData(sensorData);
					result.setTransferId(httpData.getTransferId());
					results.add(result);
				}
			}
			return new ResponseEntity<List<Result>>(results,HttpStatus.OK);
		}
		return new ResponseEntity<List<Result>>(new ArrayList<Result>(),HttpStatus.NO_CONTENT);
	}

}
