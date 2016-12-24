/**
 * 
 */
package org.pjay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vijayk
 *
 */
@Controller
// IMP: use value instead of name attribute for requestmappings, then you will not get "nested exception is java.lang.IllegalStateException: Ambiguous mapping"
// IMP: http://stackoverflow.com/questions/31847230/spring-restcontroller-ambiguous-mapping-error
// http://stackoverflow.com/questions/29949423/spring-mvc-ambiguous-mapping-found-cannot-map-controller-bean-method
// IMP: http://stackoverflow.com/questions/27381781/java-spring-boot-how-to-map-my-app-root-to-index-html
// IMP: Use this in return for string mvc to redirect or forward - return "redirect:/books" or return "forward:/books" or "forward:/admin/index.html"
public class TemplateController {
	
	@RequestMapping(value="/template")
	//@RequestMapping(name="/template", method={RequestMethod.GET})
	public String getTemplate(@RequestParam(required=false, defaultValue="World", value="name") String name, Model model) {
		model.addAttribute("name", name);
		return "template";
	}
	
	@RequestMapping(value="/greeting")
	public String getGreeting(@RequestParam(required=false, defaultValue="World", value="name") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
//	@RequestMapping(value="/")
//	public String getHome() {
//		return "index";
//	}
	
}
