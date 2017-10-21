/**
 * 
 */
package org.pjay.predixalerts.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @author vkonduru3
 *
 */
// https://stackoverflow.com/questions/26864957/failed-to-convert-string-in-java-util-date-with-datetimeformatpattern-dd-mm-y
@Entity
@Table(name = "ALERT")
@XmlRootElement(name = "alert") // Required in case of producing xml response
@Component
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String location;
	private String machine;
	private String parameter;
	private String description;
	private String action;
	// Fix for get not parsing data, if you want time stamp UI send data in time stamp format ex. "yyyy-MM-dd'T'HH:mm:ss.SSSZ" other ways ISO, Unix time stamp
	// https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
	@DateTimeFormat(pattern="yyyy-MM-dd")
	//@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE)
	private Date alertDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();// Adding default value, so that it saves current server time stamp

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((alertDate == null) ? 0 : alertDate.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((machine == null) ? 0 : machine.hashCode());
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
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
		Alert other = (Alert) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (alertDate == null) {
			if (other.alertDate != null)
				return false;
		} else if (!alertDate.equals(other.alertDate))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (machine == null) {
			if (other.machine != null)
				return false;
		} else if (!machine.equals(other.machine))
			return false;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", location=" + location + ", machine=" + machine + ", parameter=" + parameter
				+ ", description=" + description + ", action=" + action + ", alertDate=" + alertDate + ", creationDate="
				+ creationDate + "]";
	}

	// Note: Not Used, just in case if we get it combined one Ex: ABC001:Engine-Pressure
	// private String machineParameter;

}
