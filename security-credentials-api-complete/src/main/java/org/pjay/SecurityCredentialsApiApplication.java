package org.pjay;

import org.h2.server.web.WebServlet;
import org.pjay.repository.UserInfoRepository;
import org.pjay.repository.UserSecurityInfoRepository;
import org.pjay.repository.UserTokenInfoRepository;
import org.pjay.util.DummyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
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
//http://stackoverflow.com/questions/24655684/spring-boot-default-h2-jdbc-connection-and-h2-console

//IMP: cors filter
//https://spring.io/guides/gs/rest-service-cors/
//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cors.html
//https://spring.io/guides/gs/securing-web/
//http://stackoverflow.com/questions/31995221/correct-use-of-websecurity-in-websecurityconfigureradapter

//IMP: have spring-boot-starter-web, spring-boot-starter-security dependency this only had jpa
//http://stackoverflow.com/questions/30958968/spring-framework-unable-to-start-embedded-container

// IMP: http://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
// http://stackoverflow.com/questions/15922991/is-spring-annotation-controller-same-as-service
// http://javapapers.com/spring/spring-component-service-repository-controller-difference/

//IMP: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file
//http://stackoverflow.com/questions/27904594/spring-boot-war-deployed-to-tomcat

@SpringBootApplication
public class SecurityCredentialsApiApplication extends SpringBootServletInitializer{
	
	private static final Logger log = LoggerFactory.getLogger(SecurityCredentialsApiApplication.class);
	
	@Autowired
	DummyData dummyData;
	/*
		@Autowired
		Results results;
	*/
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SecurityCredentialsApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityCredentialsApiApplication.class, args);
	}

	// Register the h2 database servlet to start or run for accessing the DB
	// console
	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
		// servletRegistrationBean.setServlet(new WebServlet());
		servletRegistrationBean.addUrlMappings("/h2-console/*");
		return servletRegistrationBean;
	}
	
	@Bean
	public CommandLineRunner bootInitialization(UserInfoRepository infoRepository, UserSecurityInfoRepository securityInfoRepository, UserTokenInfoRepository tokenInfoRepository){
		return (args) -> {
			//log.info("results.toString():: \t" + results.toString());
			log.info("=============================================================");
			log.info("dummyData.getUserInfo():: \t" + dummyData.getUserInfo().toString());
			log.info("dummyData.getUserTokenInfo():: \t" + dummyData.getUserTokenInfo().toString());
			log.info("dummyData.getUserSecurityInfo():: \t" + dummyData.getUserSecurityInfo().toString());
			//System.out.println("dummyData.getUserInfo():: \t" + dummyData.getUserInfo().toString());
			//System.out.println("dummyData.getUserTokenInfo():: \t" + dummyData.getUserTokenInfo().toString());
			//System.out.println("dummyData.getUserSecurityInfo():: \t" + dummyData.getUserSecurityInfo().toString());
			log.info("=========================== Save Data =========================");
			infoRepository.save(dummyData.getUserInfo());
			securityInfoRepository.save(dummyData.getUserSecurityInfo());
			tokenInfoRepository.save(dummyData.getUserTokenInfo());
			
			/**
				Hibernate: insert into user_info (user_id, asset_id, first_name, last_name, user_name) values (null, ?, ?, ?, ?)
				Hibernate: insert into user_security_info (security_id, user_name) values (null, ?)
				Hibernate: insert into security_question_answer (qna_id, answer, qusetion) values (null, ?, ?)
				Hibernate: insert into security_question_answer (qna_id, answer, qusetion) values (null, ?, ?)
				Hibernate: insert into security_question_answer (qna_id, answer, qusetion) values (null, ?, ?)
				Hibernate: insert into security_question_answer (qna_id, answer, qusetion) values (null, ?, ?)
				Hibernate: insert into security_question_answer (qna_id, answer, qusetion) values (null, ?, ?)
				Hibernate: insert into user_security_qna (security_id, qna_id) values (?, ?)
				Hibernate: insert into user_security_qna (security_id, qna_id) values (?, ?)
				Hibernate: insert into user_security_qna (security_id, qna_id) values (?, ?)
				Hibernate: insert into user_security_qna (security_id, qna_id) values (?, ?)
				Hibernate: insert into user_security_qna (security_id, qna_id) values (?, ?)
				Hibernate: insert into user_token_info (user_token_id, token_id, user_name) values (null, ?, ?)
			 */
		};
	}
	
}
