/**
 * 
 */
package org.pjay.repository;

import org.pjay.entity.UserSecurityInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author vijayk
 *
 */
public interface UserSecurityInfoRepository extends CrudRepository<UserSecurityInfo, Long> {
	
	UserSecurityInfo getByUserName(String userName);
	
	// https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html
	// http://levelup.lishman.com/hibernate/hql/joins.php
	// http://www.journaldev.com/2954/hibernate-query-language-hql-example-tutorial
	// Not used
	/**
		@Query("from UserSecurityInfo as usf join usf.questionNAnswers as qna where usf.userName = :username and qna.question = :question and LOWER(qna.answer) = LOWER(:answer)")
		List<Object[]> isAnswerCorrect(String username, String question, String answer);
	*/

}
