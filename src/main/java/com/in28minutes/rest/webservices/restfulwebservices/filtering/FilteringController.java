package com.in28minutes.rest.webservices.restfulwebservices.filtering;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1", "value3213121", "value12");
    }
    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveSomeListOfBean(){
        return Arrays.asList(
                new SomeBean("value1", "value3213121", "value12"),
                new SomeBean("value143211", "val1242", "value112341234232")
        );

    }
}
