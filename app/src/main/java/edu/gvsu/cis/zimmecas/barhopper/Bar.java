package edu.gvsu.cis.zimmecas.barhopper;

/**
 * Created by Casey on 4/19/2016.
 */
public class Bar {
    private String name;
    private String address;
    private String phoneNumber;

    public Bar(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getPhoneNumber(){return phoneNumber;}
    public void setName(String n){name = n;}
    public void setAddress(String a){address = a;}
    public void setPhoneNumber(String pN){phoneNumber = pN;}
}
