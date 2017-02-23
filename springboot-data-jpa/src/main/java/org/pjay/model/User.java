/**
 * 
 */
package org.pjay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * @author vijayk
 *
 */

// V-IMP: http://stackoverflow.com/questions/23456197/spring-data-jpa-repository-underscore-on-entity-column-name
// _ is a reserve character in Spring Data query derivation
// https://github.com/konduruvijaykumar/spring-data-examples/blob/master/jpa/example/src/main/java/example/springdata/jpa/simple/User.java
// http://stackoverflow.com/questions/3126366/doing-an-in-query-with-hibernate
// http://stackoverflow.com/questions/38337453/customizing-spring-data-repository-bean-names-for-use-with-multiple-data-sources

@Entity
@NamedQuery(name = "User.findByTheUsersFirstName", query = "from User u where u.first_name = ?1")
public class User {

	public User() {
	}

	public User(String user_name, String first_name, String last_name) {
		this.user_name = user_name;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public User(Long id, String user_name, String first_name, String last_name) {
		this.id = id;
		this.user_name = user_name;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String user_name;

	private String first_name;

	private String last_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		User other = (User) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", first_name=" + first_name + ", last_name=" + last_name
				+ "]";
	}

}
