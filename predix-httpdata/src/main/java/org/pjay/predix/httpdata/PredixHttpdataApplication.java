package org.pjay.predix.httpdata;

import org.h2.server.web.WebServlet;
import org.pjay.predix.httpdata.entity.HttpData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PredixHttpdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PredixHttpdataApplication.class, args);
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
	// @Scope("request")
	// request scope issue as mentioned below
	/**
		org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'httpDataController': Unsatisfied dependency expressed through field 'httpDataService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'httpDataServiceImpl': Unsatisfied dependency expressed through field 'httpData'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'gethttpData': Scope 'request' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton; nested exception is java.lang.IllegalStateException: No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread? If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet/DispatcherPortlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.
	 */
	public HttpData gethttpData() {
		return new HttpData();
	}
}
