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
    String _address;
    String _dob;
    String _gender;
    String _maritalStatus;
    /*String[] _pastMedicalHistory;
    String[] _lifestyle;
    String[] _familyHistory;*/
    InsuranceDetails _insuranceDetails;
    EmergencyContact _emergencyContact;

    // Empty constructor
    public Patient(){

    }
    // constructors


    public Patient(String _id, String _password, String _name, String _phone_number) {
        this._id = _id;
        this._password = _password;
        this._name = _name;
        this._phone_number = _phone_number;
    }

    public Patient(String id, String password, String name, String _phone_number, String _address, String _dob, String _gender, String _maritalStatus, String _insuranceCompany, String _insuranceId, String _groupNumber, String _eName, String _phoneNumber, String _relationship){
        this._id = id;
        this._password = password;
        this._name = name;
        this._phone_number = _phone_number;
        this._address = _address;
        this._dob = _dob;
        this._gender = _gender;
        this._maritalStatus = _maritalStatus;
        this.set_insuranceDetails(new InsuranceDetails(_insuranceCompany, _insuranceId, _groupNumber));
        this.set_emergencyContact(new EmergencyContact(_eName, _phoneNumber, _relationship));
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

    public InsuranceDetails get_insuranceDetails() {
        return _insuranceDetails;
    }

    public void set_insuranceDetails(InsuranceDetails _insuranceDetails) {
        this._insuranceDetails = _insuranceDetails;
    }

    public EmergencyContact get_emergencyContact() {
        return _emergencyContact;
    }

    public void set_emergencyContact(EmergencyContact _emergencyContact) {
        this._emergencyContact = _emergencyContact;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_dob() {
        return _dob;
    }

    public void set_dob(String _dob) {
        this._dob = _dob;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_maritalStatus() {
        return _maritalStatus;
    }

    public void set_maritalStatus(String _maritalStatus) {
        this._maritalStatus = _maritalStatus;
    }

   /* public String[] get_pastMedicalHistory() {
        return _pastMedicalHistory;
    }

    public void set_pastMedicalHistory(String[] _pastMedicalHistory) {
        this._pastMedicalHistory = _pastMedicalHistory;
    }

    public String[] get_lifestyle() {
        return _lifestyle;
    }

    public void set_lifestyle(String[] _lifestyle) {
        this._lifestyle = _lifestyle;
    }

    public String[] get_familyHistory() {
        return _familyHistory;
    }

    public void set_familyHistory(String[] _familyHistory) {
        this._familyHistory = _familyHistory;
    }*/
}
