/**
 * 
 */
package org.pjay.repository;

import java.util.List;

import org.pjay.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author vijayk
 *
 */
// V-IMP: http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
// https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

	UserInfo findByUserName(String username);

	UserInfo getByAssetId(String assetid);

	List<UserInfo> findByFirstNameAndLastName(String firstname, String lastname);

	List<UserInfo> readByFirstNameOrLastName(String first, String last);

	List<UserInfo> readByFirstName(String first_name);

	List<UserInfo> getByLastName(String name);
	
	@Query("delete from UserInfo u where u.userName = :userName")
	void removeByUserName(String userName);
	
	@Query("UPDATE UserInfo u SET u.userName = :userName WHERE u.userName = :userName")
	void updateUserName(String userName);
	
	@Query("UPDATE UserInfo u SET u.assetId = :assetId WHERE u.userName = :userName")
	void updateAssetIDByUserName(String userName, String assetId);
	
	@Query("update UserInfo uInfo SET uInfo.assetId = :assetId where uInfo.assetId = :assetId")
	void updateAssetID(String assetId);
	
	@Query("update UserInfo u SET u.userName = :userName, u.assetId = :assetId where u.userName = :userName")
	void updateUserNameAndAssetID(String userName, String assetId);

}
