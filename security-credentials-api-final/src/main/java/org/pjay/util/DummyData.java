/**
 * 
 */
package org.pjay.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pjay.entity.SecurityQuestionAnswer;
import org.pjay.entity.UserInfo;
import org.pjay.entity.UserSecurityInfo;
import org.pjay.entity.UserTokenInfo;
import org.springframework.stereotype.Component;

/**
 * @author vijayk
 *
 */
// Required to identify this resource by spring, can try and verify configuration annotation
// Component should be good as we have SpringBootApplication - (SecurityCredentialsApiApplication) starter as configuration class
@Component
public class DummyData {

	// IMP: If i have this @Bean configuration for all this objects then every where autowire of this classes will create below data objects.
	// Hence using them as methods only and can add @Component to main classes if you want so that only fresh object is given instead of dummy data
	//@Bean
	// Not used
	public UserInfo getUserInfo() {
		return new UserInfo("vijayk", "Vijay", "Konduru", "AE144238");
	}

	//@Bean
	// Not used
	public UserTokenInfo getUserTokenInfo() {
		return new UserTokenInfo("vijayk", "ad2db6754cd144c6b02b95ec");
	}

	//@Bean
	// Not used
	public UserSecurityInfo getUserSecurityInfo() {
		SecurityQuestionAnswer sqa1 = getSecurityQuestionAnswer();
		sqa1.setQuestion("Mother's maiden name?");
		sqa1.setAnswer("Nancy");
		SecurityQuestionAnswer sqa2 = getSecurityQuestionAnswer();
		sqa2.setQuestion("Best friend in school?");
		sqa2.setAnswer("Peter");
		SecurityQuestionAnswer sqa3 = getSecurityQuestionAnswer();
		sqa3.setQuestion("Name of the first pet?");
		sqa3.setAnswer("Sparky");
		SecurityQuestionAnswer sqa4 = getSecurityQuestionAnswer();
		sqa4.setQuestion("Make of the first car?");
		sqa4.setAnswer("Porsche");
		SecurityQuestionAnswer sqa5 = getSecurityQuestionAnswer();
		sqa5.setQuestion("Favorite sport?");
		sqa5.setAnswer("Billiards");
		Set<SecurityQuestionAnswer> sqaData = new HashSet<>();
		sqaData.add(sqa1);
		sqaData.add(sqa2);
		sqaData.add(sqa3);
		sqaData.add(sqa4);
		sqaData.add(sqa5);
		return new UserSecurityInfo("vijayk", sqaData);
	}

	//@Bean
	// Still works without below annotation
	//@Scope("prototype")
	private SecurityQuestionAnswer getSecurityQuestionAnswer() {
		return new SecurityQuestionAnswer();
	}
	
	public List<UserInfo> getUserInfoList() {
		UserInfo info1 = new UserInfo("tim@pjay.com", "Tim", "Buchalka", "AE456789");
		UserInfo info2 = new UserInfo("andy@pjay.com", "Andy", "Labat", "AE876589");
		UserInfo info3 = new UserInfo("snow@pjay.com", "Snow", "White", "AE345890");
		UserInfo info4 = new UserInfo("john@pjay.com", "John", "Doe", "AE998768");
		UserInfo info5 = new UserInfo("vijay@pjay.com", "Vijay", "Konduru", "AE876905");
		List<UserInfo> userInfos = new ArrayList<>(Arrays.asList(info1,info2,info3,info4,info5));
		return userInfos;
	}
	
	public List<UserTokenInfo> getUserTokenInfoList() {
		UserTokenInfo tokenInfo1 = new UserTokenInfo("tim@pjay.com", "ad2db6754cd144c6b02b95ec");
		UserTokenInfo tokenInfo2 = new UserTokenInfo("andy@pjay.com", "5d88eb954a844e09a26aacfd");
		UserTokenInfo tokenInfo3 = new UserTokenInfo("snow@pjay.com", "6ef18ebd7b45472a87a89438");
		UserTokenInfo tokenInfo4 = new UserTokenInfo("john@pjay.com", "7d308dfa2e4e4e9580bb9a4f");
		UserTokenInfo tokenInfo5 = new UserTokenInfo("vijay@pjay.com", "bac38b724adb4022ac1ff0aa");
		List<UserTokenInfo> tokenInfos = new ArrayList<>(Arrays.asList(tokenInfo1,tokenInfo2,tokenInfo3,tokenInfo4,tokenInfo5));
		return tokenInfos;
	}
	
	private SecurityQuestionAnswer getSecurityQuestionAnswer(String question, String answer) {
		return new SecurityQuestionAnswer(question, answer);
	}
	
	public List<UserSecurityInfo> getUserSecurityInfoList() {
		// -- 1
		SecurityQuestionAnswer user1sqa1 = getSecurityQuestionAnswer("Mother's maiden name?", "Nancy");
		SecurityQuestionAnswer user1sqa2 = getSecurityQuestionAnswer("Best friend in school?", "Peter");
		SecurityQuestionAnswer user1sqa3 = getSecurityQuestionAnswer("Name of the first pet?", "Sparky");
		SecurityQuestionAnswer user1sqa4 = getSecurityQuestionAnswer("Make of the first car?", "Porsche");
		SecurityQuestionAnswer user1sqa5 = getSecurityQuestionAnswer("Favorite sport?", "Billiards");
		Set<SecurityQuestionAnswer> user1sqaData1 = new HashSet<>(Arrays.asList(user1sqa1,user1sqa2,user1sqa3,user1sqa4,user1sqa5));
		UserSecurityInfo securityInfo1 = new UserSecurityInfo("tim@pjay.com", user1sqaData1);
		// -- 2
		SecurityQuestionAnswer user2sqa1 = getSecurityQuestionAnswer("Mother's maiden name?", "amy");
		SecurityQuestionAnswer user2sqa2 = getSecurityQuestionAnswer("Best friend in school?", "Nancy");
		SecurityQuestionAnswer user2sqa3 = getSecurityQuestionAnswer("Name of the first pet?", "Snoopy");
		SecurityQuestionAnswer user2sqa4 = getSecurityQuestionAnswer("Make of the first car?", "Aston Martin");
		SecurityQuestionAnswer user2sqa5 = getSecurityQuestionAnswer("Favorite sport?", "Chess");
		Set<SecurityQuestionAnswer> user2sqaData1 = new HashSet<>(Arrays.asList(user2sqa1,user2sqa2,user2sqa3,user2sqa4,user2sqa5));
		UserSecurityInfo securityInfo2 = new UserSecurityInfo("andy@pjay.com", user2sqaData1);
		// --3
		SecurityQuestionAnswer user3sqa1 = getSecurityQuestionAnswer("Mother's maiden name?", "Julie");
		SecurityQuestionAnswer user3sqa2 = getSecurityQuestionAnswer("Best friend in school?", "Tim");
		SecurityQuestionAnswer user3sqa3 = getSecurityQuestionAnswer("Name of the first pet?", "Tweety");
		SecurityQuestionAnswer user3sqa4 = getSecurityQuestionAnswer("Make of the first car?", "Audi");
		SecurityQuestionAnswer user3sqa5 = getSecurityQuestionAnswer("Favorite sport?", "Hockey");
		Set<SecurityQuestionAnswer> user3sqaData1 = new HashSet<>(Arrays.asList(user3sqa1,user3sqa2,user3sqa3,user3sqa4,user3sqa5));
		UserSecurityInfo securityInfo3 = new UserSecurityInfo("snow@pjay.com", user3sqaData1);
		// --4
		SecurityQuestionAnswer user4sqa1 = getSecurityQuestionAnswer("Mother's maiden name?", "Jennifer");
		SecurityQuestionAnswer user4sqa2 = getSecurityQuestionAnswer("Best friend in school?", "Prince");
		SecurityQuestionAnswer user4sqa3 = getSecurityQuestionAnswer("Name of the first pet?", "Tomy");
		SecurityQuestionAnswer user4sqa4 = getSecurityQuestionAnswer("Make of the first car?", "Lamborghini");
		SecurityQuestionAnswer user4sqa5 = getSecurityQuestionAnswer("Favorite sport?", "Cricket");
		Set<SecurityQuestionAnswer> user4sqaData1 = new HashSet<>(Arrays.asList(user4sqa1,user4sqa2,user4sqa3,user4sqa4,user4sqa5));
		UserSecurityInfo securityInfo4 = new UserSecurityInfo("john@pjay.com", user4sqaData1);
		// --5
		SecurityQuestionAnswer user5sqa1 = getSecurityQuestionAnswer("Mother's maiden name?", "Brenda");
		SecurityQuestionAnswer user5sqa2 = getSecurityQuestionAnswer("Best friend in school?", "Jennifer");
		SecurityQuestionAnswer user5sqa3 = getSecurityQuestionAnswer("Name of the first pet?", "Super");
		SecurityQuestionAnswer user5sqa4 = getSecurityQuestionAnswer("Make of the first car?", "Ferrari");
		SecurityQuestionAnswer user5sqa5 = getSecurityQuestionAnswer("Favorite sport?", "badminton");
		Set<SecurityQuestionAnswer> user5sqaData1 = new HashSet<>(Arrays.asList(user5sqa1,user5sqa2,user5sqa3,user5sqa4,user5sqa5));
		UserSecurityInfo securityInfo5 = new UserSecurityInfo("vijay@pjay.com", user5sqaData1);
		return new ArrayList<>(Arrays.asList(securityInfo1,securityInfo2,securityInfo3,securityInfo4,securityInfo5));
	}

}
