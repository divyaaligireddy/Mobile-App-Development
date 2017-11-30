package com.app.gsu.housecallmd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.app.gsu.housecallmd.entity.DoctorNurse;
import com.app.gsu.housecallmd.entity.EmergencyContact;
import com.app.gsu.housecallmd.entity.InsuranceDetails;
import com.app.gsu.housecallmd.entity.Patient;
import com.app.gsu.housecallmd.entity.Profession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 11-15-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "houseCall";

    // Patients table name
    private static final String TABLE_PATIENTS = "patient";

    // DoctorNurse table name
    private static final String TABLE_DOCTOR_NURSE = "doctor_nurse";

    // Patients Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    //private static final String[] PATIENT_COLUMNS = {"id", "password", "name", "phone_number", "address", "dob", "gender", "maritalStatus", "insuranceCompany", "insuranceId", "groupNumber", "eName", "phoneNumber", "relationship"};

    // DoctorNurse Table Columns names
    private static final String KEY_DOC_ID = "id";
    private static final String KEY_DOC_NAME = "name";
    private static final String KEY_PROFESSION = "profession";
    private static final String KEY_SPECIALIZATION = "specialization";
    private static final String KEY_RATE = "hourly_rate";
    private static final String KEY_AVAILABILITY = "availability";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTS_TABLE = createPatientTable();
        db.execSQL(CREATE_PATIENTS_TABLE);

        String CREATE_DOCTOR_NURSE_TABLE = "CREATE TABLE " + TABLE_DOCTOR_NURSE + "("
                + KEY_DOC_ID + " TEXT PRIMARY KEY," + KEY_DOC_NAME + " TEXT," + KEY_PROFESSION + " TEXT,"
                + KEY_SPECIALIZATION + " TEXT," + KEY_RATE + " INT,"
                + KEY_AVAILABILITY + " TEXT" + ")";
        Log.d("Creating Table: ", CREATE_DOCTOR_NURSE_TABLE);
        db.execSQL(CREATE_DOCTOR_NURSE_TABLE);
        //db.close();
    }

    @NonNull
    private String createPatientTable() {
        String CREATE_PATIENTS_TABLE = "CREATE TABLE " + TABLE_PATIENTS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_PASSWORD + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT,address TEXT,dob TEXT,gender TEXT,maritalStatus TEXT,"
                + "insuranceCompany TEXT,insuranceId TEXT,groupNumber TEXT,eName TEXT,phoneNumber TEXT,"
                + "relationship TEXT" + ")";
        Log.d("Creating Table: ", CREATE_PATIENTS_TABLE);
        return CREATE_PATIENTS_TABLE;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR_NURSE);
        // Create tables again
        onCreate(db);
        //db.close();
    }

    // Adding new Patient
    public void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, patient.getID());
        values.put(KEY_PASSWORD, patient.get_password());
        values.put(KEY_NAME, patient.getName());
        values.put(KEY_PH_NO, patient.getPhoneNumber());
        values.put("address", patient.get_address());
        values.put("dob", patient.get_dob());
        values.put("gender", patient.get_gender());
        values.put("maritalStatus", patient.get_maritalStatus());
        if(patient.get_insuranceDetails() != null) {
            values.put("insuranceCompany", patient.get_insuranceDetails().get_insuranceCompany());
            values.put("insuranceId", patient.get_insuranceDetails().get_insuranceId());
            values.put("groupNumber", patient.get_insuranceDetails().get_groupNumber());
        }
        if(patient.get_emergencyContact() != null) {
            values.put("phoneNumber", patient.get_emergencyContact().get_phoneNumber());
            values.put("relationship", patient.get_emergencyContact().get_relationship());
            values.put("eName", patient.get_emergencyContact().get_name());
        }

        // Inserting Row
        db.insert(TABLE_PATIENTS, null, values);
        //db.close();
    }

    // Getting single Patient
    public Patient getPatient(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Patient patient = null;
        Cursor cursor = db.query(TABLE_PATIENTS, new String[] {KEY_ID, KEY_PASSWORD, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[] {id}, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            System.out.println(cursor.getString(0));
            System.out.println(cursor.getString(1));
            System.out.println(cursor.getString(2));
            System.out.println(cursor.getString(3));
            //System.out.println(cursor.getString(4));
            patient = new Patient(cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.close();
        }
       // db.close();
        return patient;
    }

    // Adding new Doctor/Nurse
    public void addDoctorNurse(DoctorNurse doctorNurse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOC_ID, doctorNurse.getEmailId());
        values.put(KEY_DOC_NAME, doctorNurse.getName());
        values.put(KEY_PROFESSION, doctorNurse.getProfession().name());
        values.put(KEY_SPECIALIZATION, doctorNurse.getSpecialization());
        values.put(KEY_RATE, doctorNurse.getHourlyRate().intValue());
        //values.put(KEY_AVAILABILITY, doctorNurse.getAvailability().toString());

        // Inserting Row
        db.insert(TABLE_DOCTOR_NURSE, null, values);
        //db.close();
    }

    // Getting All Doctors/Nurses
    public List<DoctorNurse> getAllDoctorNurse() {
        List<DoctorNurse> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOCTOR_NURSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DoctorNurse doctorNurse = new DoctorNurse();
                doctorNurse.setEmailId(cursor.getString(0));
                doctorNurse.setName(cursor.getString(1));
                doctorNurse.setProfession(Profession.valueOf(cursor.getString(2)));
                doctorNurse.setSpecialization(cursor.getString(3));
                doctorNurse.setHourlyRate(Integer.parseInt(cursor.getString(4)));
                //doctorNurse.setAvailability(Arrays.asList(cursor.getString(5)));

                // Adding contact to list
                list.add(doctorNurse);
            } while (cursor.moveToNext());
        }

        //db.close();
        return list;
    }

    // Getting All Patients
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient();
                patient.setID(cursor.getString(0));
                patient.set_password(cursor.getString(1));
                patient.setName(cursor.getString(2));
                patient.setPhoneNumber(cursor.getString(3));
                patient.set_address(cursor.getString(4));
                patient.set_dob(cursor.getString(5));
                patient.set_gender(cursor.getString(6));
                patient.set_maritalStatus(cursor.getString(7));
                patient.set_insuranceDetails(new InsuranceDetails(cursor.getString(8), cursor.getString(9), cursor.getString(10)));
                patient.set_emergencyContact(new EmergencyContact(cursor.getString(11), cursor.getString(12), cursor.getString(13)));

                // Adding contact to list
                list.add(patient);
            } while (cursor.moveToNext());
        }

        //db.close();
        return list;
    }

}
