/**
 * 
 */
package org.pjay.predix.httpdata.service;

import java.util.List;

import org.pjay.predix.httpdata.bean.HttpDataBean;
import org.pjay.predix.httpdata.entity.HttpData;

/**
 * @author vkonduru3
 *
 */
public interface HttpDataService {
	
	void saveHttpData(HttpDataBean httpDataBean);
	
	List<HttpData> getHttpData(String transferId);
	
	List<HttpData> getHttpData();

}
