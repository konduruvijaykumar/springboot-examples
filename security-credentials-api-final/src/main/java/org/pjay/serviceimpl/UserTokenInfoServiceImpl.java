/**
 * 
 */
package org.pjay.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.pjay.entity.UserTokenInfo;
import org.pjay.repository.UserTokenInfoRepository;
import org.pjay.service.UserTokenInfoService;
import org.springframework.stereotype.Service;

/**
 * @author vijayk
 *
 */
// Service already has @Component capability hence no need to add component annotation
@Service(value="userTokenInfoService")
public class UserTokenInfoServiceImpl implements UserTokenInfoService {
	
	private final UserTokenInfoRepository userTokenInfoRepository;
	
	public UserTokenInfoServiceImpl(UserTokenInfoRepository userTokenInfoRepository) {
		this.userTokenInfoRepository = userTokenInfoRepository;
	}

	@Override
	public List<UserTokenInfo> getAllUserTokenInfo() {
		Iterable<UserTokenInfo> results = userTokenInfoRepository.findAll();
		if(results instanceof List){
			return (List<UserTokenInfo>)results;
		}
		List<UserTokenInfo> resultList =  new ArrayList<>();
		for (UserTokenInfo userTokenInfo : results) {
			resultList.add(userTokenInfo);
		}
		return resultList;
	}

	@Override
	public UserTokenInfo getByUserName(String username) {
		return userTokenInfoRepository.readByUserName(username);
	}

}
