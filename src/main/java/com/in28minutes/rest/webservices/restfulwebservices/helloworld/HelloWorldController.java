package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //this one just returns a string
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    //now create a bean and return it back
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    //now create a path parameter
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    //internationalization. So depending on the language passed in the header a diff output is given
    //
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
//            @RequestHeader(name="Accept-Language", required = false) Locale locale
    ) {

        //the LocalContextHolder.getLocale thing lets us not have to pu tin teh @RequestHeader stuff above every time
        return messageSource.getMessage("good.morning.message", null, "Default Message!!", LocaleContextHolder.getLocale());
    }


}
