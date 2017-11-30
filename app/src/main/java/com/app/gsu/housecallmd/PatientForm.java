package com.app.gsu.housecallmd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.gsu.housecallmd.entity.EmergencyContact;
import com.app.gsu.housecallmd.entity.InsuranceDetails;
import com.app.gsu.housecallmd.entity.Patient;

public class PatientForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void submitForm(View view) {

        // Patient Details
        EditText patientName = (EditText) findViewById(R.id.EditTextPatientName);
        EditText patientAddress = (EditText) findViewById(R.id.EditTextPatientAddress);
        EditText patientDoB = (EditText) findViewById(R.id.EditTextDoB);
        EditText patientEmail = (EditText) findViewById(R.id.EditEmailInfo);
        EditText patientPassword = (EditText) findViewById(R.id.EditPassword);
        EditText patientPhone = (EditText) findViewById(R.id.EditPhoneInfo);
        Spinner patientGender = (Spinner) findViewById(R.id.SpinnerGenderType);
        Spinner patientMarital = (Spinner) findViewById(R.id.SpinnerMaritalType);

        // Patient Insurance
        EditText patientInsName = (EditText) findViewById(R.id.EditTextInsName);
        EditText patientInsId = (EditText) findViewById(R.id.EditInsuranceIdInfo);
        EditText patientInsGrp = (EditText) findViewById(R.id.EditGroupNumberInfo);

        Patient patient = new Patient();
        patient.setName(patientName.getText().toString());
        patient.set_address(patientAddress.getText().toString());
        patient.set_dob(patientDoB.getText().toString());
        patient.setID(patientEmail.getText().toString());
        patient.set_password(patientPassword.getText().toString());
        patient.setPhoneNumber(patientPhone.getText().toString());
        patient.set_gender(patientGender.getSelectedItem().toString());
        patient.set_maritalStatus(patientMarital.getSelectedItem().toString());

        InsuranceDetails insuranceDetails = new InsuranceDetails();
        insuranceDetails.set_insuranceCompany(patientInsName.getText().toString());
        insuranceDetails.set_insuranceId(patientInsId.getText().toString());
        insuranceDetails.set_groupNumber(patientInsGrp.getText().toString());

        patient.set_insuranceDetails(insuranceDetails);

        // Emergency Contact details
        EditText patientEname = (EditText) findViewById(R.id.EditTextEmergencyName);
        EditText patientEPhone = (EditText) findViewById(R.id.EditEmergencyPhoneInfo);
        Spinner patientEType = (Spinner) findViewById(R.id.SpinnerEmergencyStatusType);

        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.set_name(patientEname.getText().toString());
        emergencyContact.set_phoneNumber(patientEPhone.getText().toString());
        emergencyContact.set_relationship(patientEType.getSelectedItem().toString());

        patient.set_emergencyContact(emergencyContact);

        // Adding patient to database
        DatabaseHandler db = new DatabaseHandler(this);
        db.addPatient(patient);

        Intent intent = new Intent(PatientForm.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}
