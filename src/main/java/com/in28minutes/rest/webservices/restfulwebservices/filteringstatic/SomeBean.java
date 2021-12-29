package com.in28minutes.rest.webservices.restfulwebservices.filteringstatic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//This class is for FILTERING


//this annotation designates the values to ignore
//this one requires hardcoding though so the @JsonIgnore method above fields3 might be better
@JsonIgnoreProperties(value = {"fields1", "fields2"})
public class SomeBean {

    private String fields1;
    private String fields2;


    //JsonIgnore annotation hides this field... potential use case: if it were a password
    //use json.jackson annotation for the import
    //commented it out because the class-level annotation @JsonIgnoreProperties does a similar thing but there you
    //give the specific values
//    @JsonIgnore
    private String fields3;


    public SomeBean(String fields1, String fields2, String fields3) {
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
