package com.app.gsu.housecallmd;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.gsu.housecallmd.entity.DoctorNurse;
import com.app.gsu.housecallmd.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class ListOfDoctorNurseActivity extends ListActivity {
    public static final String CONF_INFO = "com.app.gsu.housecallmd.Confirmation";
    public static final String PATIENT_INFO = "com.app.gsu.housecallmd.Patient";
    String message = "There are no doctors or nurses available in your location. We apologize for the inconvenience. Please check back later.";
    private Patient patient = null;
    List<DoctorNurse> doctorNurseList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra(LoginActivity.PATIENT_INFO);

        if (patient == null) {
            patient = (Patient) intent.getSerializableExtra(Confirmation.PATIENT_INFO);
        }

        doctorNurseList = db.getAllDoctorNurseBasedOnLocation(patient.get_address());
        if(!doctorNurseList.isEmpty())
            setListAdapter(new ArrayAdapter<DoctorNurse>(this, android.R.layout.simple_list_item_1, doctorNurseList));
        else {
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{message}));
        }

    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Intent intent = new Intent(ListOfDoctorNurseActivity.this, Confirmation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(CONF_INFO, doctorNurseList.get(position));
        intent.putExtra(PATIENT_INFO, patient);
        startActivity(intent);
    }

}
