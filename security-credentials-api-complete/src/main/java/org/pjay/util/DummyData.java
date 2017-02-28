/**
 * 
 */
package org.pjay.util;

import java.util.HashSet;
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
// Component should be good as we have SpringBootApplication -(SecurityCredentialsApiApplication) starter as configuration class
@Component
public class DummyData {

	// IMP: If i have this @Bean configuration for all this objects then every where autowire of this classes will create below data objects.
	// Hence using them as methods only and can add @Component to main classes if you want so that only fresh object is given instead of dummy data
	//@Bean
	public UserInfo getUserInfo() {
		return new UserInfo("vijayk", "Vijay", "Konduru", "AE144238");
	}

	//@Bean
	public UserTokenInfo getUserTokenInfo() {
		return new UserTokenInfo("vijayk", "ad2db6754cd144c6b02b95ec");
	}

	//@Bean
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

}
