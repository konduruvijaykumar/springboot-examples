package org.pjay;

import org.h2.server.web.WebServlet;
import org.pjay.model.User;
import org.pjay.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

//IMP: h2 DB
//http://stackoverflow.com/questions/24655684/spring-boot-default-h2-jdbc-connection-and-h2-console
//https://dzone.com/articles/using-the-h2-database-console-in-spring-boot-with
//https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/

//IMP: Error - ServletRegistrationBean cannot be resolved to a type
//http://stackoverflow.com/questions/15781902/javax-servlet-servletexception-cannot-be-resolved-to-a-type-in-spring-web-app
//http://stackoverflow.com/questions/30737617/the-type-javax-servlet-servletcontext-and-javax-servlet-servletexception-cannot
//http://stackoverflow.com/questions/13951127/servletexception-httpservletresponse-and-httpservletrequest-cannot-be-resolved
//http://stackoverflow.com/questions/20915528/how-can-i-register-a-secondary-servlet-with-spring-boot

//IMP: application properties configuration
//https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
//https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html

//IMP: cors filter
//https://spring.io/guides/gs/rest-service-cors/
//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cors.html
//https://spring.io/guides/gs/securing-web/
//http://stackoverflow.com/questions/31995221/correct-use-of-websecurity-in-websecurityconfigureradapter

//IMP: have spring-boot-starter-web, spring-boot-starter-security dependency this only had jpa
//http://stackoverflow.com/questions/30958968/spring-framework-unable-to-start-embedded-container

//IMP: if you run as maven install you will see the dB schema name which is for test cases before generating war. if want to use a different name change properties in application.properties
//2017-02-23 17:53:22.046  INFO 10476 --- [           main] o.s.j.d.e.EmbeddedDatabaseFactory        : Starting embedded database: url='jdbc:h2:mem:86baaa14-547c-4232-95d0-4c5733cc8da7;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false', username='sa'
//2017-02-23 17:53:22.510  INFO 10476 --- [           main] j.LocalContainerEntityManagerFactoryBean : Building JPA container EntityManagerFactory for persistence unit 'default'

@SpringBootApplication
/**
 * @author vijayk
 *
 */
public class SpringbootDataJpaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringbootDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDataJpaApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/h2-console/*");
	    return registration;
	}
	
	@Bean
	public CommandLineRunner createDemoData(UserRepository repository){
		// CommandLineRunner has only one method to be implemented run with args to be passed, hence can be used as lambda
		/*
			return (input) -> {
				
			};
		*/
		// above implementation also works fine
		return (args) -> {
			
			log.info("Users insert:");
			// save a couple of users
			repository.save(new User("vjay","Vijay","Konduru"));
			repository.save(new User("john","John","Doe"));
			repository.save(new User("andy","Andy","Labat"));
			repository.save(new User("prince","Prince","White"));
			repository.save(new User("vjay1","Vijay","Konduru"));
			
			// fetch all users
			log.info("findall() Users:");
			log.info("===========================================");
			repository.findAll().forEach(System.out::println);
			log.info("");
			
			
			log.info("findOne() User");
			User user = repository.findOne(1L);
			log.info("User found with findOne(1L):");
			log.info("===========================================");
			log.info(user.toString());
			log.info("");
			
			
			log.info("User found with findByUserName(vjay):");
			log.info("===========================================");
			log.info(repository.findByUserName("vjay").toString());
			log.info("");
			
			// fetch customers by last name
			log.info("User found with findByLastName(Kondur):");
			log.info("===========================================");
			for (User userbyLastName : repository.findByLastName("Konduru")) {
				log.info(userbyLastName.toString());
			}
			log.info("");
			
			log.info("User found with findByTheUsersFirstName(Vijay):");
			log.info("===========================================");
			log.info(repository.findByTheUsersFirstName("Vijay").toString());
			log.info("");
			
			log.info("User found with findByFirstOrLastName(White):");
			log.info("===========================================");
			//log.info(repository.findByFirstOrLastName("Konduru").toString());
			log.info(repository.findByFirstOrLastName("White").toString());
			log.info("");
			
			log.info("User found with findByFirstnameOrLastname(user):");
			log.info("===========================================");
			log.info(repository.findByFirstnameOrLastname(user).toString());
			log.info("");
			
			log.info("User found with findByFirstnameAndLastname(firstname, lastname):");
			log.info("===========================================");
			//log.info(repository.findByFirstnameAndLastname("Vijay","Konduru").toString());
			log.info(repository.findByFirstnameAndLastname("John","Doe").toString());
			log.info("");
			
		};
		
	}
}
