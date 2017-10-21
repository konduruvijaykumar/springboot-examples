package org.pjay.predixalerts;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PredixalertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PredixalertsApplication.class, args);
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
	
}
