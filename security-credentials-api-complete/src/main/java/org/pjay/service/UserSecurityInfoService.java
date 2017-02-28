/**
 * 
 */
package org.pjay.service;

import java.util.List;

import org.pjay.entity.UserSecurityInfo;

/**
 * @author vijayk
 *
 */
public interface UserSecurityInfoService {
	
	List<UserSecurityInfo> getAllUserSecurityInfo();
	
	UserSecurityInfo getByUserName(String userName);

}
