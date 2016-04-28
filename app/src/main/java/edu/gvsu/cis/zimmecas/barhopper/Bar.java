package edu.gvsu.cis.zimmecas.barhopper;

import java.io.Serializable;

/**
 * Created by Casey on 4/19/2016.
 */
public class Bar implements Serializable{
    private String href;
    private String name;
    private String address;
    private String phoneNumber;

    public Bar(){
        name = "";
        address = "";
        phoneNumber = "";
    }

    public Bar(String href, String name, String address, String phoneNumber){
        this.href = href;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getHref() {return href;}
    public void setName(String n){name = n;}
    public void setAddress(String a){address = a;}
    public void setPhoneNumber(String pN){phoneNumber = pN;}
    public void setHref(String s) {href = s;}
}
