package com.app.gsu.housecallmd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    }
    public void onDoctorNurseSelected(){
        Intent intent = new Intent(User.this, RegistrationActivity.class);
        startActivity(intent);
    }
    public void onPatientSelected(){
        Intent intent = new Intent(User.this, PatientForm.class);
        startActivity(intent);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_patient:
                if (checked)
                    onPatientSelected();
                    break;
            case R.id.radio_Doctor:
                if (checked)
                    onDoctorNurseSelected();
                    break;
            case R.id.radio_Nurse:
                if (checked)
                    onDoctorNurseSelected();
                break;

        }
    }

}
