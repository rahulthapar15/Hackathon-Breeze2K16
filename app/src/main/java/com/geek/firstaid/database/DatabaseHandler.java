package com.geek.firstaid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geek.firstaid.utilities.PatientInfoBean;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";



    private static final String KEY_ID = "regid";
    private static final String KEY_NAME = "name";
    private static final String KEY_SEX = "sex";
    private static final String KEY_AGE= "age";
    private static final String KEY_CITY = "city";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_DISEASE = "disease";
    private static final String KEY_DATE = "date";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" +KEY_ID + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_SEX + " TEXT,"+KEY_ADDRESS+" TEXT,"+KEY_AGE+" TEXT," +KEY_MOBILE+ " TEXT,"+KEY_DISEASE+ " TEXT,"+KEY_DATE+ " TEXT,"+KEY_CITY+" TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
   public void addContact(PatientInfoBean contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_SEX, contact.getSex());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_AGE, contact.getAge());
        values.put(KEY_MOBILE, contact.getMobile());
        values.put(KEY_DISEASE, contact.getDisease());
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_CITY, contact.getCity());

        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }



    // Getting All Contacts
    public List<PatientInfoBean> getAllContacts() {
        List<PatientInfoBean> contactList = new ArrayList<PatientInfoBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PatientInfoBean contact = new PatientInfoBean();
                contact.setId(cursor.getString(0));
                contact.setName(cursor.getString(1));
                contact.setSex(cursor.getString(2));
                contact.setAddress(cursor.getString(3));
                contact.setAge(cursor.getString(4));
                contact.setMobile(cursor.getString(5));
                contact.setDisease(cursor.getString(6));
                contact.setDate(cursor.getString(7));
                contact.setCity(cursor.getString(8));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single contact
    public int updateContact(PatientInfoBean contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DISEASE,contact.getDisease());
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }
    public PatientInfoBean getLastRecord(){

        List<PatientInfoBean> contactList = new ArrayList<PatientInfoBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PatientInfoBean contact = new PatientInfoBean();
                contact.setId(cursor.getString(0));
                contact.setName(cursor.getString(1));
                contact.setSex(cursor.getString(2));
                contact.setAddress(cursor.getString(3));
                contact.setAge(cursor.getString(4));
                contact.setMobile(cursor.getString(5));
                contact.setDisease(cursor.getString(6));
                contact.setDate(cursor.getString(7));
                contact.setCity(cursor.getString(8));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return  contactList.get(contactList.size()-1);
    }
}
