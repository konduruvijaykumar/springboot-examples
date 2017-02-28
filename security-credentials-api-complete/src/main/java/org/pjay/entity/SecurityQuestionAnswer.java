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
@Table(name = "SECURITY_QUESTION_ANSWER")
@XmlRootElement(name="securityquestionanswer")// Required in case of producing xml response
@Component
public class SecurityQuestionAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QNA_ID")
	private Long id;

	@Column(name = "QUSETION")
	private String question;

	@Column(name = "ANSWER")
	private String answer;

	public SecurityQuestionAnswer() {
	}

	public SecurityQuestionAnswer(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public SecurityQuestionAnswer(Long id, String question, String answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
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
		SecurityQuestionAnswer other = (SecurityQuestionAnswer) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SecurityQuestionAnswer [" + (id != null ? "id=" + id + ", " : "")
				+ (question != null ? "question=" + question + ", " : "") + (answer != null ? "answer=" + answer : "")
				+ "]";
	}

}
