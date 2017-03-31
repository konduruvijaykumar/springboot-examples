/**
 * 
 */
package org.pjay.service;

import java.util.List;

import org.pjay.entity.UserInfo;

/**
 * @author vijayk
 *
 */
public interface UserInfoService {
	
	List<UserInfo> getAllUserInfo();
	
	UserInfo getByUserName(String username);
	
	UserInfo getByAssetId(String assetid);

}
