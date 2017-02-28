/**
 * 
 */
package org.pjay.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.pjay.entity.UserSecurityInfo;
import org.pjay.repository.UserSecurityInfoRepository;
import org.pjay.service.UserSecurityInfoService;
import org.springframework.stereotype.Service;

/**
 * @author vijayk
 *
 */
// Service already has @Component capability hence no need to add component annotation
@Service
public class UserSecurityInfoServiceImpl implements UserSecurityInfoService{
	
	private final UserSecurityInfoRepository userSecurityInfoRepository;
	
	public UserSecurityInfoServiceImpl(UserSecurityInfoRepository userSecurityInfoRepository) {
		this.userSecurityInfoRepository = userSecurityInfoRepository;
	}

	@Override
	public List<UserSecurityInfo> getAllUserSecurityInfo() {
		Iterable<UserSecurityInfo> results = userSecurityInfoRepository.findAll();
		if(results instanceof List){
			return (List<UserSecurityInfo>)results;
		}
		List<UserSecurityInfo> resultList =  new ArrayList<>();
		for (UserSecurityInfo userSecurityInfo : results) {
			resultList.add(userSecurityInfo);
		}
		return resultList;
	}

	@Override
	public UserSecurityInfo getByUserName(String userName) {
		return userSecurityInfoRepository.getByUserName(userName);
	}

}
