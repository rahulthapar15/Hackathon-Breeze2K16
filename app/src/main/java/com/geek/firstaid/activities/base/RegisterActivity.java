package com.geek.firstaid.activities.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.ChooseDiseaseActivity;
import com.geek.firstaid.database.DatabaseHandler;
import com.geek.firstaid.utilities.PatientInfoBean;
import com.geek.firstaid.utilities.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppBaseActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextAddress;
    private RadioGroup radioGroup;
    private String sex = "male";

    private SessionManager mSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbar(getString(R.string.title_activity_register_patient), true);
        setStatusBarColorResource(R.color.theme_status_bar);
        mSessionManager=new SessionManager(this);
        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextAge = (EditText) findViewById(R.id.editText_age);
        editTextAddress = (EditText) findViewById(R.id.editText_address);
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);

        sex = getResources().getString(R.string.male);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sound) {
                    sex = getResources().getString(R.string.male);
                    mSessionManager.saveGender(false);
                } else if (checkedId == R.id.vibration) {
                    sex = getResources().getString(R.string.female);
                    mSessionManager.saveGender(true);
                }
            }

        });

    }

    public void onRegisterTap(View view) {
        if (validate()) {
            Toast.makeText(this, R.string.details_error_msg, Toast.LENGTH_LONG).show();
        } else {

            if(sex.equalsIgnoreCase(getResources().getString(R.string.male))){
                mSessionManager.saveGender(false);
            }else{
                mSessionManager.saveGender(true);
            }


            PatientInfoBean infoBean = new PatientInfoBean();
            infoBean.setName(editTextName.getText().toString().trim());
            infoBean.setAge(editTextAge.getText().toString());
            infoBean.setSex(sex);
            infoBean.setCity("");
            infoBean.setAddress(editTextAddress.getText().toString().trim());
            infoBean.setMobile("");
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            infoBean.setDate(formattedDate);

            DatabaseHandler handler = new DatabaseHandler(RegisterActivity.this);
            handler.addContact(infoBean);

            startActivity(new Intent(RegisterActivity.this, ChooseDiseaseActivity.class));
        }
    }

    private boolean validate() {
        boolean ret = false;

        if (TextUtils.isEmpty(editTextName.getText().toString())) {
            ret = true;
        }
        if (editTextName.getText().toString().trim().length()==0) {
            ret = true;
        }
        if (TextUtils.isEmpty(editTextAge.getText().toString())) {
            ret = true;
        }
        if (TextUtils.isEmpty(editTextAddress.getText().toString())) {
            ret = true;
        }
        if (editTextAddress.getText().toString().trim().length()==0) {
            ret = true;
        }
        return ret;
    }
}
