/**
 * 
 */
package org.pjay.repository;

import java.util.List;

import org.pjay.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author vijayk
 *
 */
// https://github.com/konduruvijaykumar/spring-data-examples
// https://github.com/konduruvijaykumar/spring-data-examples/tree/master/jpa
// https://github.com/konduruvijaykumar/spring-data-examples/tree/master/jpa/example
// https://github.com/konduruvijaykumar/spring-data-examples/blob/master/jpa/example/src/main/java/example/springdata/jpa/simple/SimpleUserRepository.java
public interface UserRepository extends CrudRepository<User, Long> {
	
	//User findByUserName(String user_name);
	@Query("from User u where u.user_name = :userName")
	//@Query("select u from User u where u.user_name = :userName")
	User findByUserName(String userName);
	
	@Query("select u from User u where u.last_name = :lastname")
	List<User> findByLastName(String lastname);
	
	// Issue:
	// http://stackoverflow.com/questions/13072502/get-invalid-derived-query-error-all-over-the-place-in-our-spring-data-jparepos
	// https://issuetracker.springsource.com/browse/STS-3848
	
	// http://stackoverflow.com/questions/30788173/name-for-parameter-binding-must-not-be-null-or-empty-for-named-parameters-you-n
	// IMP: http://stackoverflow.com/questions/27733892/java-8-spring-data-jpa-parameter-binding
	// Uses named query defined in User object
	// Error: Invalid derived query! No property theUsersFirstName found for type User! -- Please find solution
	// Error: nested exception is java.lang.IllegalArgumentException: Name for parameter binding must not be null or empty! For named parameters you need to use @Param for query method parameters on Java versions < 8.
	List<User> findByTheUsersFirstName(@Param("first_name") String firstname);
	
	// As name of parameter name matches with User object query is formed automatically to get by first_name -- This is not correct
	// Looks like all methods name have a standard findBy<Object property>, removeBy<Object property> etc. with first letter upper case like in getter and setter for property
	// Error: Invalid derived query! No property firstName found for type User! Did you mean 'first_name'?  -- Please find solution
	// Runtime also similar error No property firstName found for type User! Did you mean 'first_name'?
	//List<User> findByFirstName(String first_name);
	// Below code is also not working only hope to refactor and stop working with _ in param names
	// runtime error No property first found for type User!
	//List<User> findByFirst_name(@Param("first_name") String first_name);
	
	@Query("select u from User u where u.first_name = :name or u.last_name = :name")
	List<User> findByFirstOrLastName(String name);
	
	// Return all the users with the given firstname or lastname. Makes use of SpEL (Spring Expression Language).
	@Query("select u from User u where u.first_name = :#{#user.first_name} or u.last_name = :#{#user.last_name}")
	List<User> findByFirstnameOrLastname(User user);
	
	@Query("select u from User u where u.first_name = :firstname and u.last_name = :lastname")
	List<User> findByFirstnameAndLastname(String firstname, String lastname);
}
