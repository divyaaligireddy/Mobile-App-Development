package com.app.gsu.housecallmd;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gsu.housecallmd.entity.DoctorNurse;
import com.app.gsu.housecallmd.entity.Patient;

import static android.R.attr.button;
import static com.app.gsu.housecallmd.LoginActivity.PATIENT_INFO;
import static com.app.gsu.housecallmd.R.layout.activity_confirmation;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
//        Intent intent = getIntent();
//        DoctorNurse doctorNurse = (DoctorNurse) intent.getSerializableExtra(ListOfDoctorNurseActivity.CONF_INFO);
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
                                startActivity(intent);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void Cancel(View view) {
        Intent intent = new Intent(Confirmation.this, ListOfDoctorNurseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
