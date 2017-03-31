/**
 * 
 */
package org.pjay.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

/**
 * @author vijayk
 *
 */
// Required to identify this resource by spring for auto wiring and instance
// creation
@Component
@XmlRootElement(name = "error") // Required in case of producing xml response
public class Error {

	private Integer status;
	private String errorMessage;

	public Error() {
		// TODO Auto-generated constructor stub
	}

	public Error(Integer status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Error other = (Error) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Error [status=" + status + ", errorMessage=" + errorMessage + "]";
	}

}
