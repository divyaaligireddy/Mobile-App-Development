package com.app.gsu.housecallmd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.app.gsu.housecallmd.entity.DoctorNurse;
import com.app.gsu.housecallmd.entity.Patient;
import com.app.gsu.housecallmd.entity.Profession;

import java.util.List;

/**
 * Created by Divya on 11-15-2017.
 */

public class LoginActivity extends AppCompatActivity {

    public static final String PATIENT_INFO = "com.app.gsu.housecallmd.PatientInfo";
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private Button mLogin;
    private Button mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addPatientsDoctors();

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.email_sign_in_button);
        mRegister = (Button) findViewById(R.id.create_account_button);
    }

    protected void createAccount(View v){
        Intent i = new Intent(LoginActivity.this, User.class);
        startActivity(i);
    }

    public void attemptLogin(View view) {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            /*showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);*/

            DatabaseHandler db = new DatabaseHandler(this);
            Patient patient = db.getPatient(email);
            if (patient == null) {
                mEmailView.setError(getString(R.string.error_invalid_email));
                mEmailView.requestFocus();
            }
            else if(patient.get_password().equals(password)) {
                Intent intent = new Intent(LoginActivity.this, ListOfDoctorNurseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(PATIENT_INFO, patient);
                startActivity(intent);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    private void addPatientsDoctors() {
        DatabaseHandler db = new DatabaseHandler(this);
        //Checking if exist already in db before inserting
        List<Patient> patientList = db.getAllPatients();
        if(patientList.isEmpty()) {
            Log.d("Insert: ", "Inserting Patients ..");
            db.addPatient(new Patient("divya@gmail.com", "divya", "Divya", "4534333323", "Atlanta", "10/03/1990", "Female", "Married", "UHC", "3333", "123", "Ravi", "3334445566", "Spouse"));
        }

        List<DoctorNurse> doctorNurseList = db.getAllDoctorNurse();
        if(doctorNurseList.isEmpty()) {
            Log.d("Insert: ", "Inserting Doctors and Nurses ..");
            db.addDoctorNurse(new DoctorNurse("sofia@gmail.com", "Sofia", Profession.Nurse, "General Medicine", 50));
            db.addDoctorNurse(new DoctorNurse("william@gmail.com", "William", Profession.Doctor, "Cardiologist", 100));
            db.addDoctorNurse(new DoctorNurse("anand@gmail.com", "Anand", Profession.Doctor, "Family Physician", 75));
            db.addDoctorNurse(new DoctorNurse("sara@gmail.com", "Sara", Profession.Doctor, "Cardiologist", 100));
            db.addDoctorNurse(new DoctorNurse("mehta@gmail.com", "Mehta", Profession.Doctor, "Neurologist", 120));
            db.addDoctorNurse(new DoctorNurse("mahi@gmail.com", "Mahi", Profession.Doctor, "Gastroenterologist", 100));
        }
    }
}
