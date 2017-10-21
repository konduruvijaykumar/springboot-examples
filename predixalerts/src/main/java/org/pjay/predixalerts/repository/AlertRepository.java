/**
 * 
 */
package org.pjay.predixalerts.repository;

import org.pjay.predixalerts.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author vkonduru3
 *
 */
// IMP::
// https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
// http://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
// http://www.baeldung.com/rest-api-search-language-spring-data-specifications
// https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-four-jpa-criteria-queries/
// https://github.com/pkainulainen/spring-data-jpa-examples
// https://github.com/pkainulainen/spring-data-jpa-examples/tree/master/criteria-api
// http://spring.io/blog/2011/02/10/getting-started-with-spring-data-jpa
// https://stackoverflow.com/questions/19930152/what-is-persistence-context
// TODO: please try using spring specification interface to build predicates, as mentioned in few above tutorials Ex. - http://www.baeldung.com/rest-api-search-language-spring-data-specifications 
public interface AlertRepository extends JpaRepository<Alert, Long>, JpaSpecificationExecutor<Alert>{

}
