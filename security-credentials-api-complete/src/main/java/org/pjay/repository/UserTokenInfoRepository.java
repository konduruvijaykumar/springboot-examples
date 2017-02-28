/**
 * 
 */
package org.pjay.repository;

import org.pjay.entity.UserTokenInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author vijayk
 *
 */
public interface UserTokenInfoRepository extends CrudRepository<UserTokenInfo, Long> {

	UserTokenInfo readByUserName(String user_name);
	
}
