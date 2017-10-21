/**
 * 
 */
package org.pjay.predixalerts.controller;

import java.util.ArrayList;
import java.util.List;

import org.pjay.predixalerts.entity.Alert;
import org.pjay.predixalerts.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vkonduru3
 *
 */
@RestController
@RequestMapping("/")
public class AlertController {
	
	@Autowired
	AlertService alertService;
	
	@RequestMapping(value="/alertapi", method = {RequestMethod.POST})
	public void saveAlertData(@RequestBody Alert alert) {
		alertService.saveAlertData(alert);
	}
	
	@RequestMapping(value="/alertapi", method = {RequestMethod.GET})
	public ResponseEntity<List<Alert>> getAlertData(Alert alert) {
		List<Alert> list = alertService.getAlertData(alert);
		if(null != list && !list.isEmpty()) {
			return new ResponseEntity<List<Alert>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Alert>>(new ArrayList<>(), HttpStatus.NO_CONTENT);
	}

}
