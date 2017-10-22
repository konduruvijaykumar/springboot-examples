/**
 * 
 */
package org.pjay.predix.httpdata.service;

import java.util.List;

import org.pjay.predix.httpdata.bean.HttpDataBean;
import org.pjay.predix.httpdata.entity.HttpData;
import org.pjay.predix.httpdata.repository.HttpDataRepository;
import org.pjay.predix.httpdata.util.HttpDataObjectConvertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vkonduru3
 *
 */
@Service
public class HttpDataServiceImpl implements HttpDataService{
	
	@Autowired
	private HttpDataRepository httpDataRepository;
	
	@Autowired
	private HttpDataObjectConvertion httpDataObjectConvertion;
	
//	@Autowired
//	HttpData httpData;
	
	//int test = 1;

	@Override
	public void saveHttpData(HttpDataBean httpDataBean) {
		//test=test+1;
		//httpData = httpDataObjectConvertion.getHttpDataOject(httpData, httpDataBean);
		// The only fix available for bean object being reused in different request even thought prototype is create new object and work with it in every request 
		HttpData httpData = new HttpData();
		httpDataObjectConvertion.getHttpDataOject(httpData, httpDataBean);
		httpDataRepository.save(httpData);
	}

	@Override
	public List<HttpData> getHttpData(String transferId) {
		return httpDataRepository.findByTransferId(transferId);
	}

	@Override
	public List<HttpData> getHttpData() {
		return httpDataRepository.findAll();
	}

}
