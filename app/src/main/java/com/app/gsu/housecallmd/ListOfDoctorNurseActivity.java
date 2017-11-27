package com.app.gsu.housecallmd;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gsu.housecallmd.entity.DoctorNurse;
import com.app.gsu.housecallmd.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class ListOfDoctorNurseActivity extends ListActivity {
    public static final String CONF_INFO = "com.app.gsu.housecallmd.Confirmation";
    List<DoctorNurse> doctorNurseList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);

        Intent intent = getIntent();
        Patient patient = (Patient) intent.getSerializableExtra(LoginActivity.PATIENT_INFO);

        /*TextView textView = new TextView(this);
        textView.setTextSize(18);
        textView.setText("Hello " + patient.getName());

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.content);
        layout.addView(textView);*/

        doctorNurseList = db.getAllDoctorNurse();
        setListAdapter(new ArrayAdapter<DoctorNurse>(this, android.R.layout.simple_list_item_1, doctorNurseList));

    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Intent intent = new Intent(ListOfDoctorNurseActivity.this, Confirmation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(CONF_INFO,doctorNurseList.get(position));
        startActivity(intent);
    }

}
