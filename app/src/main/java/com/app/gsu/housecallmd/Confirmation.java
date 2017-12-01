package com.app.gsu.housecallmd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.app.gsu.housecallmd.entity.DoctorNurse;
import com.app.gsu.housecallmd.entity.Patient;

public class Confirmation extends AppCompatActivity {

    public static final String PATIENT_INFO = "com.app.gsu.housecallmd.Patient";
    Patient patient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        DoctorNurse doctorNurse = (DoctorNurse) intent.getSerializableExtra(ListOfDoctorNurseActivity.CONF_INFO);
        patient = (Patient) intent.getSerializableExtra(ListOfDoctorNurseActivity.PATIENT_INFO);


/*        TextView textView = new TextView(this);
        textView.setTextSize(18);
        textView.setText("Hello " + doctorNurse);*/
        TextView textView = (TextView) findViewById(R.id.TextViewName);
        textView.setText(doctorNurse.toString());
    }


    public void openDialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Confirmation.this);
        alertDialogBuilder.setMessage("Thank you for requesting an appointment. You will receive a confirmation message soon");
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Confirmation.this, ListOfDoctorNurseActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra(PATIENT_INFO, patient);
                                startActivity(intent);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void Cancel(View view) {
        Intent intent = new Intent(Confirmation.this, ListOfDoctorNurseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(PATIENT_INFO, patient);
        startActivity(intent);
    }
}
