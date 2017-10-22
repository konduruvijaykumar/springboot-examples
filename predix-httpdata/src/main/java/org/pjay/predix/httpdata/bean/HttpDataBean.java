/**
 * 
 */
package org.pjay.predix.httpdata.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author vkonduru3
 *
 */
@Component
public class HttpDataBean {

	private String transferId;
	private String riverName;
	private String contentType;
	private String contentDisposition;
	private String contentDescription;
	private String timestamp;
	private MultipartFile data;

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getRiverName() {
		return riverName;
	}

	public void setRiverName(String riverName) {
		this.riverName = riverName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public MultipartFile getData() {
		return data;
	}

	public void setData(MultipartFile data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "HttpDataBean [transferId=" + transferId + ", riverName=" + riverName + ", contentType=" + contentType
				+ ", contentDisposition=" + contentDisposition + ", contentDescription=" + contentDescription
				+ ", timestamp=" + timestamp + ", data=" + data + "]";
	}

}
