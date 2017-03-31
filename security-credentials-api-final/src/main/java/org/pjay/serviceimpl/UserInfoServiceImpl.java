/**
 * 
 */
package org.pjay.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.pjay.entity.UserInfo;
import org.pjay.repository.UserInfoRepository;
import org.pjay.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author vijayk
 *
 */
// Service already has @Component capability hence no need to add component annotation
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private final  UserInfoRepository userInfoRepository;
	
	public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@Override
	public List<UserInfo> getAllUserInfo() {
		Iterable<UserInfo> results = userInfoRepository.findAll();
		if(results instanceof List){
			return (List<UserInfo>)results;
		}
		List<UserInfo> resultList =  new ArrayList<>();
		for (UserInfo userInfo : results) {
			resultList.add(userInfo);
		}
		return resultList;
	}

	@Override
	public UserInfo getByUserName(String username) {
		return userInfoRepository.findByUserName(username);
	}

	@Override
	public UserInfo getByAssetId(String assetid) {
		return userInfoRepository.getByAssetId(assetid);
	}

}
