/**
 * 
 */
package org.pjay.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

/**
 * @author vijayk
 *
 */

// https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-one-to-many-using-annotations-1.html
@Entity
@Table(name = "USER_SECURITY_INFO")
@XmlRootElement(name="usersecurityinfo")// Required in case of producing xml response
@Component
public class UserSecurityInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SECURITY_ID")
	private Long id;

	@Column(name = "USER_NAME", nullable = false, unique = true)
	private String userName;

	// 0ne to Many mapping
	@OneToMany(cascade = CascadeType.ALL)//fetch=FetchType.EAGER
	// Can also use joinColumns={@JoinColumn(name="USER_NAME")}
	@JoinTable(name = "USER_SECURITY_QNA", joinColumns = { @JoinColumn(name = "SECURITY_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "QNA_ID") })
	@Fetch(FetchMode.JOIN)
	// NOTE: Fetch mode is working fine here, this other five queries are fetching qna for each specific user.
	// Due to this we have extra 5 queries, for fetching each collection via join table in 1 - M  
	/**
	 * Note: below comment is not correct, working fine verify above comments for more info on this
	 * Note: We can observer 1+N queries being fired i.e., 1+5 (1 for user_security_info and 5 for user_security_qna tables). To solve this use Fetch annotation with FetchMode.JOIN
		Hibernate: select usersecuri0_.security_id as security1_2_, usersecuri0_.user_name as user_nam2_2_ from user_security_info usersecuri0_
		Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
		Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
		Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
		Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
		Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
	 */
	private Set<SecurityQuestionAnswer> questionNAnswers;

	public UserSecurityInfo() {
	}

	public UserSecurityInfo(String userName, Set<SecurityQuestionAnswer> questionNAnswers) {
		this.userName = userName;
		this.questionNAnswers = questionNAnswers;
	}

	public UserSecurityInfo(Long id, String userName, Set<SecurityQuestionAnswer> questionNAnswers) {
		this.id = id;
		this.userName = userName;
		this.questionNAnswers = questionNAnswers;
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

	public Set<SecurityQuestionAnswer> getQuestionNAnswers() {
		return questionNAnswers;
	}

	public void setQuestionNAnswers(Set<SecurityQuestionAnswer> questionNAnswers) {
		this.questionNAnswers = questionNAnswers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questionNAnswers == null) ? 0 : questionNAnswers.hashCode());
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
		UserSecurityInfo other = (UserSecurityInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questionNAnswers == null) {
			if (other.questionNAnswers != null)
				return false;
		} else if (!questionNAnswers.equals(other.questionNAnswers))
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
		return "UserSecurityInfo [" + (id != null ? "id=" + id + ", " : "")
				+ (userName != null ? "userName=" + userName + ", " : "")
				+ (questionNAnswers != null ? "questionNAnswers=" + questionNAnswers : "") + "]";
	}

}
