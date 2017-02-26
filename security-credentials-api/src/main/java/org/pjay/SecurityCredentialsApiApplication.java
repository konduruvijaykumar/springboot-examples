package org.pjay;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

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

@SpringBootApplication()
//@SpringBootApplication(exclude={ServletInitializer.class})
public class SecurityCredentialsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityCredentialsApiApplication.class, args);
	}

	// Register the h2 database servlet to start or run for accessing the DB
	// console
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
		// servletRegistrationBean.setServlet(new WebServlet());
		servletRegistrationBean.addUrlMappings("/h2-console/*");
		return servletRegistrationBean;
	}
	
	
}
