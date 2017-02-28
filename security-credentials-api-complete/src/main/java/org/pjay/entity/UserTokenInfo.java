/**
 * 
 */
package org.pjay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

/**
 * @author vijayk
 *
 */

@Entity
@Table(name = "USER_TOKEN_INFO")
@XmlRootElement(name="usertokeninfo")// Required in case of producing xml response
@Component
public class UserTokenInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_TOKEN_ID")
	private Long id;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "TOKEN_ID", nullable = false)
	private String tokeId;

	public UserTokenInfo() {
	}

	public UserTokenInfo(String userName, String tokeId) {
		this.userName = userName;
		this.tokeId = tokeId;
	}

	public UserTokenInfo(Long id, String userName, String tokeId) {
		this.id = id;
		this.userName = userName;
		this.tokeId = tokeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTokeId() {
		return tokeId;
	}

	public void setTokeId(String tokeId) {
		this.tokeId = tokeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tokeId == null) ? 0 : tokeId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserTokenInfo other = (UserTokenInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tokeId == null) {
			if (other.tokeId != null)
				return false;
		} else if (!tokeId.equals(other.tokeId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserTokenInfo [" + (id != null ? "id=" + id + ", " : "")
				+ (userName != null ? "userName=" + userName + ", " : "") + (tokeId != null ? "tokeId=" + tokeId : "")
				+ "]";
	}

}
