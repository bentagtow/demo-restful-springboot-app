package com.in28minutes.rest.webservices.restfulwebservices.filteringdynamic;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//This class is for DYNAMIC FILTERING
//for some requests send field1
//for some requests hide field2
//etc...
//this filtering happens in the controller


//defines the filter that we created in the controller
@JsonFilter("SomeBeanFilter")
public class DynamicFilteringSomeBean {

    private String fields1;
    private String fields2;
    private String fields3;


    public DynamicFilteringSomeBean(String fields1, String fields2, String fields3) {
        super();
        this.fields1 = fields1;
        this.fields2 = fields2;
        this.fields3 = fields3;
    }

    public String getFields1() {
        return fields1;
    }

    public void setFields1(String fields1) {
        this.fields1 = fields1;
    }

    public String getFields2() {
        return fields2;
    }

    public void setFields2(String fields2) {
        this.fields2 = fields2;
    }

    public String getFields3() {
        return fields3;
    }

    public void setFields3(String fields3) {
        this.fields3 = fields3;
    }
}
