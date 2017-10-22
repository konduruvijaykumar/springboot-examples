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
public class SensorData {

	private Float temperature;
	private Float humidity;

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getHumidity() {
		return humidity;
	}

	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SensorData))
			return false;
		SensorData other = (SensorData) obj;
		if (humidity == null) {
			if (other.humidity != null)
				return false;
		} else if (!humidity.equals(other.humidity))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SensorData [temperature=" + temperature + ", humidity=" + humidity + "]";
	}

}
