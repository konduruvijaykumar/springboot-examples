/**
 * 
 */
package org.pjay.predix.httpdata.bean;

import org.springframework.stereotype.Component;

/**
 * @author vkonduru3
 *
 */
@Component
public class Result {

	private String transferId;
	private SensorData data;

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public SensorData getData() {
		return data;
	}

	public void setData(SensorData data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((transferId == null) ? 0 : transferId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Result))
			return false;
		Result other = (Result) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (transferId == null) {
			if (other.transferId != null)
				return false;
		} else if (!transferId.equals(other.transferId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Result [transferId=" + transferId + ", data=" + data + "]";
	}

}
