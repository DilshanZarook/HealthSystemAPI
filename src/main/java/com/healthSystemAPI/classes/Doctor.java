package com.healthSystemAPI.classes;

public class Doctor extends Person {
    private String doctorSpeciality;
    
    public Doctor() {}
    
    public Doctor(int personId,String name, String contactNo, String address, String doctorSpeciality) {
        super(personId,name,contactNo,address);
        this.doctorSpeciality = doctorSpeciality;
    }
    

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }
      
}
