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

}
