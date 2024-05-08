package com.healthSystemAPI.classes;

public class Person {
    private int personId;
    private String name;
    private String contactNo;
    private String address;
    
    public Person(){
    }
    
    
    public Person(int personId,String name, String contactNo, String address){
        this.personId = personId;
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
    }
    
    public int getPersonId(){
        return personId;
    }
    
    public void setPersonId(int personId){
        this.personId = personId;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name =name;
    }
    
    public String getContactNo(){
        return contactNo;
    }
    
    public void setContactNo(String contactNo){
        this.contactNo = contactNo;
    }
    
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    
}
