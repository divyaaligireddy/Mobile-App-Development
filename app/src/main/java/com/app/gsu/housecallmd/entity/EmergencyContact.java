package com.app.gsu.housecallmd.entity;

import java.io.Serializable;

/**
 * Created by Divya on 11-29-2017.
 */

public class EmergencyContact implements Serializable {
    String _eName;
    String _phoneNumber;
    String _relationship;

    public EmergencyContact() {
    }

    public EmergencyContact(String _eName, String _phoneNumber, String _relationship) {
        this._eName = _eName;
        this._phoneNumber = _phoneNumber;
        this._relationship = _relationship;
    }

    public String get_name() {
        return _eName;
    }

    public void set_name(String _name) {
        this._eName = _name;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String get_relationship() {
        return _relationship;
    }

    public void set_relationship(String _relationship) {
        this._relationship = _relationship;
    }
}
