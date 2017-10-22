/**
 * 
 */
package org.pjay.predix.httpdata.util;

import java.io.IOException;
import java.sql.Timestamp;

import org.pjay.predix.httpdata.bean.HttpDataBean;
import org.pjay.predix.httpdata.entity.HttpData;
import org.springframework.stereotype.Component;

/**
 * @author vkonduru3
 *
 */
@Component
public class HttpDataObjectConvertion {
	
	public HttpData getHttpDataOject(HttpData httpData, HttpDataBean httpDataBean){
		httpData.setContentDescription(httpDataBean.getContentDescription());
		httpData.setContentDisposition(httpDataBean.getContentDisposition());
		httpData.setContentType(httpDataBean.getContentType());
		// Note:
		/**
			java.lang.IllegalArgumentException: Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
				at java.sql.Timestamp.valueOf(Timestamp.java:204) ~[na:1.8.0_141]
				at org.pjay.predix.httpdata.util.HttpDataObjectConvertion.getHttpDataOject(HttpDataObjectConvertion.java:31) ~[classes/:na]
				at org.pjay.predix.httpdata.service.HttpDataServiceImpl.saveHttpData(HttpDataServiceImpl.java:35) ~[classes/:na]
				at org.pjay.predix.httpdata.controller.HttpDataController.saveHttpData(HttpDataController.java:45) ~[classes/:na]
		 */
		try {
			httpData.setData(httpDataBean.getData().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpData.setRiverName(httpDataBean.getRiverName());
		httpData.setTimestamp(Timestamp.valueOf(httpDataBean.getTimestamp()).getTime());
		httpData.setTransferId(httpDataBean.getTransferId());
		return httpData;
	}

}
