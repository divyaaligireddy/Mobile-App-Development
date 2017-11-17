package com.app.gsu.housecallmd.entity;

import java.io.Serializable;

/**
 * Created by Divya on 11-15-2017.
 */

public class Patient implements Serializable {
    //private variables
    String _id;
    String _password;
    String _name;
    String _phone_number;

    // Empty constructor
    public Patient(){

    }
    // constructor
    public Patient(String id, String password, String name, String _phone_number){
        this._id = id;
        this._password = password;
        this._name = name;
        this._phone_number = _phone_number;
    }

    // getting ID
    public String getID(){
        return this._id;
    }

    // setting id
    public void setID(String id){
        this._id = id;
    }

    // getting password
    public String get_password() {
        return _password;
    }

    // setting password
    public void set_password(String _password) {
        this._password = _password;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
