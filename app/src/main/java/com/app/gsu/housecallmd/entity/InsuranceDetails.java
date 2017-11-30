package com.app.gsu.housecallmd.entity;

import java.io.Serializable;

/**
 * Created by Divya on 11-29-2017.
 */

public class InsuranceDetails implements Serializable {
    String _insuranceCompany;
    String _insuranceId;
    String _groupNumber;

    public InsuranceDetails() {
    }

    public InsuranceDetails(String _insuranceCompany, String _insuranceId, String _groupNumber) {
        this._insuranceCompany = _insuranceCompany;
        this._insuranceId = _insuranceId;
        this._groupNumber = _groupNumber;
    }

    public String get_insuranceCompany() {
        return _insuranceCompany;
    }

    public void set_insuranceCompany(String _insuranceCompany) {
        this._insuranceCompany = _insuranceCompany;
    }

    public String get_insuranceId() {
        return _insuranceId;
    }

    public void set_insuranceId(String _insuranceId) {
        this._insuranceId = _insuranceId;
    }

    public String get_groupNumber() {
        return _groupNumber;
    }

    public void set_groupNumber(String _groupNumber) {
        this._groupNumber = _groupNumber;
    }
}
