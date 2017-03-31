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

// IMP: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file
// http://stackoverflow.com/questions/27904594/spring-boot-war-deployed-to-tomcat

@SpringBootApplication
public class SecurityCredentialsApiApplication extends SpringBootServletInitializer{
	
	private static final Logger log = LoggerFactory.getLogger(SecurityCredentialsApiApplication.class);
	
	@Autowired
	DummyData dummyData;
	/*
		@Autowired
		Results results;
	*/
	
	// IMP: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SecurityCredentialsApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityCredentialsApiApplication.class, args);
	}

	// https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
	// Register the h2 database servlet to start or run for accessing the DB console
	// https://github.com/spring-projects/spring-boot/commit/ec8b94f13cdd8e31bf8de7489eba26438b352ab5
	// https://github.com/thewca/tnoodle/issues/86
	// http://stackoverflow.com/questions/17803718/view-content-of-embedded-h2-database-started-by-spring
	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
		// servletRegistrationBean.setServlet(new WebServlet());
		servletRegistrationBean.addUrlMappings("/h2-console/*");
		servletRegistrationBean.addInitParameter("webAllowOthers", "true");
		return servletRegistrationBean;
	}
	
	@Bean
	public CommandLineRunner bootInitialization(UserInfoRepository infoRepository, UserSecurityInfoRepository securityInfoRepository, UserTokenInfoRepository tokenInfoRepository){
		return (args) -> {
			//log.info("results.toString():: \t" + results.toString());
			//log.info("=============================================================");
			//log.info("dummyData.getUserInfo():: \t" + dummyData.getUserInfo().toString());
			//log.info("dummyData.getUserTokenInfo():: \t" + dummyData.getUserTokenInfo().toString());
			//log.info("dummyData.getUserSecurityInfo():: \t" + dummyData.getUserSecurityInfo().toString());
			//System.out.println("dummyData.getUserInfo():: \t" + dummyData.getUserInfo().toString());
			//System.out.println("dummyData.getUserTokenInfo():: \t" + dummyData.getUserTokenInfo().toString());
			//System.out.println("dummyData.getUserSecurityInfo():: \t" + dummyData.getUserSecurityInfo().toString());
			//log.info("=========================== Save Data =========================");
			//infoRepository.save(dummyData.getUserInfo());
			//securityInfoRepository.save(dummyData.getUserSecurityInfo());
			//tokenInfoRepository.save(dummyData.getUserTokenInfo());
			
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
			
			log.info("=========================== Save Data =========================");
			dummyData.getUserInfoList().forEach(userinfo -> infoRepository.save(userinfo));
			dummyData.getUserSecurityInfoList().forEach(usersecurityinfo -> securityInfoRepository.save(usersecurityinfo));
			dummyData.getUserTokenInfoList().forEach(usertokeninfo -> tokenInfoRepository.save(usertokeninfo));
			
			// Testing - I am not sure it is working as expected, i am getting complete list of qna with in UserSecurityInfo and another specific SecurityQuestionAnswer object with conditions passed
			// while this can used to check if answer was correct or not. Please verify correct procedure. I am getting arraylist with array of objects in each index.
			// arraylist 0 index position array of objects in which 0 index position UserSecurityInfo with complete qna list and 1 index position SecurityQuestionAnswer object with conditions passed
			
			/**
				List<Object[]> temp = securityInfoRepository.isAnswerCorrect("ujose@csc.com", "Mother's maiden name?", "NanCy");
				List<Object[]> temp1 = securityInfoRepository.isAnswerCorrect("ujose@csc.com", "Make of the first car?", "POrsCHE");
				if(null != temp)
					System.out.println(temp.toString());
				if(null != temp1)
					System.out.println(temp1.toString());
			*/
			
			/**
				Hibernate: select usersecuri0_.security_id as security1_2_0_, securityqu2_.qna_id as qna_id1_0_1_, usersecuri0_.user_name as user_nam2_2_0_, securityqu2_.answer as answer2_0_1_, securityqu2_.qusetion as qusetion3_0_1_ from user_security_info usersecuri0_ inner join user_security_qna questionna1_ on usersecuri0_.security_id=questionna1_.security_id inner join security_question_answer securityqu2_ on questionna1_.qna_id=securityqu2_.qna_id where usersecuri0_.user_name=? and securityqu2_.qusetion=? and lower(securityqu2_.answer)=lower(?)
				Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
				Hibernate: select usersecuri0_.security_id as security1_2_0_, securityqu2_.qna_id as qna_id1_0_1_, usersecuri0_.user_name as user_nam2_2_0_, securityqu2_.answer as answer2_0_1_, securityqu2_.qusetion as qusetion3_0_1_ from user_security_info usersecuri0_ inner join user_security_qna questionna1_ on usersecuri0_.security_id=questionna1_.security_id inner join security_question_answer securityqu2_ on questionna1_.qna_id=securityqu2_.qna_id where usersecuri0_.user_name=? and securityqu2_.qusetion=? and lower(securityqu2_.answer)=lower(?)
				Hibernate: select questionna0_.security_id as security1_3_0_, questionna0_.qna_id as qna_id2_3_0_, securityqu1_.qna_id as qna_id1_0_1_, securityqu1_.answer as answer2_0_1_, securityqu1_.qusetion as qusetion3_0_1_ from user_security_qna questionna0_ inner join security_question_answer securityqu1_ on questionna0_.qna_id=securityqu1_.qna_id where questionna0_.security_id=?
			 */
		};
	}
	
	// IMP: Console Log
	/**
		2017-03-31 17:50:31.629  INFO 4600 --- [           main] org.hibernate.tool.hbm2ddl.SchemaExport  : HHH000230: Schema export complete
		2017-03-31 17:50:31.711  INFO 4600 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
		2017-03-31 17:50:32.072  INFO 4600 --- [           main] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
		2017-03-31 17:50:32.426  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/userinfo/getbyassetid],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserInfoController.getByAssetId(java.lang.String)
		2017-03-31 17:50:32.428  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/userinfo/getbyusername],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserInfoController.getByUserName(java.lang.String)
		2017-03-31 17:50:32.428  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/userinfo/getall],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserInfoController.getAllUserInfo()
		2017-03-31 17:50:32.432  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usersecurityinfo/getbyusername],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserSecurityInfoController.getByUserName(java.lang.String)
		2017-03-31 17:50:32.434  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usersecurityinfo/isanswerscorrect],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserSecurityInfoController.isAnswersCorrect(org.pjay.entity.UserSecurityInfo)
		2017-03-31 17:50:32.435  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usersecurityinfo/isanswercorrect],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserSecurityInfoController.isAnswerCorrect(java.lang.String,java.lang.String,java.lang.String)
		2017-03-31 17:50:32.436  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usersecurityinfo/getqnaforauthorization],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserSecurityInfoController.getQNAForAuthorization(java.lang.String)
		2017-03-31 17:50:32.437  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usersecurityinfo/getall],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserSecurityInfoController.getAllSecurityInfo()
		2017-03-31 17:50:32.440  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usertokeninfo/getbyusername],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserTokenInfoController.getByUserName(java.lang.String)
		2017-03-31 17:50:32.440  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/usertokeninfo/getall],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<org.pjay.model.Results> org.pjay.controller.UserTokenInfoController.getAllUserTokenInfo()
		2017-03-31 17:50:32.444  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
		2017-03-31 17:50:32.445  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
		2017-03-31 17:50:32.568  INFO 4600 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@179ece50: startup date [Fri Mar 31 17:50:25 IST 2017]; root of context hierarchy
	 */
	
}
