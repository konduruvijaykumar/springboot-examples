/**
 * 
 */
package org.pjay.predixalerts.service;

import java.util.List;

import org.pjay.predixalerts.entity.Alert;

/**
 * @author vkonduru3
 *
 */
public interface AlertService {

	void saveAlertData(Alert alert);

	List<Alert> getAlertData(Alert alert);

}
