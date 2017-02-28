/**
 * 
 */
package org.pjay.service;

import java.util.List;

import org.pjay.entity.UserTokenInfo;

/**
 * @author vijayk
 *
 */
public interface UserTokenInfoService {
	
	List<UserTokenInfo> getAllUserTokenInfo();
	
	UserTokenInfo getByUserName(String username);

}
