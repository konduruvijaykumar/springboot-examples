/**
 * 
 */
package org.pjay.predix.httpdata.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author vkonduru3
 *
 */
@Entity
@Table(name="HTTP_DATA")
//@Component
//@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
// Note: above line will create problem when creating entity by Hibernate, as will also try to create proxy before some operations and it already a proxy abject
/**
	java.lang.IllegalArgumentException: Unknown entity: org.pjay.predix.httpdata.entity.HttpData$$EnhancerBySpringCGLIB$$31b8f12b
		at org.hibernate.jpa.spi.AbstractEntityManagerImpl.persist(AbstractEntityManagerImpl.java:1149) ~[hibernate-entitymanager-5.0.11.Final.jar:5.0.11.Final]
 */
// VIMP:
// http://www.baeldung.com/spring-bean-scopes
// https://howtodoinjava.com/spring/spring-core/spring-bean-scopes/
// http://javasampleapproach.com/spring-framework/spring-bean-scope-using-annotation-singleton-prototype-request-session-global-session-application
// https://stackoverflow.com/questions/7088790/when-to-use-request-scoped-beans-over-singleton-beans-for-controllers-in-spring-mvc
//@Scope("prototype")
//@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// Not working as expected, still same abject between different requests observed
// https://stackoverflow.com/questions/11139571/scope-of-a-spring-controller-and-its-instance-variables
//@Scope("request")
// Note: Above code will throw error during booting or server startup as other classes are singleton and will be created while server start and has bean with request scope as dependency, which cannot be created during server start
/**
	org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'httpDataController': Unsatisfied dependency expressed through field 'httpDataService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'httpDataServiceImpl': Unsatisfied dependency expressed through field 'httpData'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'httpData': Scope 'request' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton; nested exception is java.lang.IllegalStateException: No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread? If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet/DispatcherPortlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.
		at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:588) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
 */
// The only possible option to avoid all this problems is to create object using new operator and get relief from this issue or give a try with @Bean definition in configuration class 
public class HttpData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String transferId;
	private String riverName;
	private String contentType;
	private String contentDisposition;
	private String contentDescription;
	private Long timestamp;
	@Lob
	private byte[] data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentDescription == null) ? 0 : contentDescription.hashCode());
		result = prime * result + ((contentDisposition == null) ? 0 : contentDisposition.hashCode());
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((riverName == null) ? 0 : riverName.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((transferId == null) ? 0 : transferId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HttpData))
			return false;
		HttpData other = (HttpData) obj;
		if (contentDescription == null) {
			if (other.contentDescription != null)
				return false;
		} else if (!contentDescription.equals(other.contentDescription))
			return false;
		if (contentDisposition == null) {
			if (other.contentDisposition != null)
				return false;
		} else if (!contentDisposition.equals(other.contentDisposition))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (riverName == null) {
			if (other.riverName != null)
				return false;
		} else if (!riverName.equals(other.riverName))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
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
		return "HttpData [id=" + id + ", transferId=" + transferId + ", riverName=" + riverName + ", contentType="
				+ contentType + ", contentDisposition=" + contentDisposition + ", contentDescription="
				+ contentDescription + ", timestamp=" + timestamp + ", data=" + Arrays.toString(data) + "]";
	}

}
