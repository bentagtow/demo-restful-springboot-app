package com.in28minutes.rest.webservices.restfulwebservices.filteringdynamic;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DynamicFilteringController {

    //helper method for our mapping
    public MappingJacksonValue mappingMethod(Object toMap){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("fields1", "fields2");

        //the filter below must be defined in the bean itself (see the class level annotation)
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        //this MappingJaxVal class is for dynamic filtering
        MappingJacksonValue mapping = new MappingJacksonValue(toMap);

        mapping.setFilters(filters);
        return mapping;
    }

    //onlysend field1, field2 here
    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue retrieveSomeBean(){
        DynamicFilteringSomeBean dynamicFilteringSomeBean = new DynamicFilteringSomeBean("dvalue1", "dvalue3213121", "dvalue12");

        return mappingMethod(dynamicFilteringSomeBean);

    }


    //onlysend field2, field3 here
    @GetMapping("/filtering-list-dynamic")
    public MappingJacksonValue retrieveSomeListOfBean(){
        List<DynamicFilteringSomeBean> dynamicFilteringSomeBeans = Arrays.asList(
                new DynamicFilteringSomeBean("dvalue1", "dvalue3213121", "dvalue12"),
                new DynamicFilteringSomeBean("dvalue143211", "dval1242", "dvalue112341234232")
        );
        return mappingMethod(dynamicFilteringSomeBeans);

    }
}
