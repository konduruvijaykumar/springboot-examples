package org.pjay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
// IMP: http://stackoverflow.com/questions/20405474/spring-boot-context-root
// http://stackoverflow.com/questions/16526248/the-element-type-meta-must-be-terminated-by-the-matching-end-tag-meta
// http://stackoverflow.com/questions/29172612/spring-boot-app-not-serving-static-content
// IMP: http://stackoverflow.com/questions/27381781/java-spring-boot-how-to-map-my-app-root-to-index-html
// http://stackoverflow.com/questions/24661289/spring-boot-not-serving-static-content
// http://stackoverflow.com/questions/35968479/spring-boot-cannot-find-index-html-under-webapp-folder
// http://stackoverflow.com/questions/21123437/how-do-i-use-spring-boot-to-serve-static-content-located-in-dropbox-folder
// IMP: http://stackoverflow.com/questions/31847230/spring-restcontroller-ambiguous-mapping-error
// IMP: http://stackoverflow.com/questions/32758601/error-with-spring-boot
// Above url shows how to exclude some default spring boot provided services when needed
// http://stackoverflow.com/questions/19732918/circular-view-path-error-spring-mvc
// http://stackoverflow.com/questions/36697663/circular-view-path-error-spring-boot
public class SpringbootMvcDemo1Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootMvcDemo1Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMvcDemo1Application.class, args);
	}
}
