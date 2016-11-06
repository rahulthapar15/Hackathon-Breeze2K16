package com.geek.firstaid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;
import com.geek.firstaid.utilities.KeyboardUtils;
import com.geek.firstaid.utilities.NetworkUtils;
import com.geek.firstaid.utilities.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppBaseActivity {

    private EditText edt_user_id;
    private EditText edt_password;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager=new SessionManager(this);
        if(sessionManager.isLogin()){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);
        setStatusBarColorResource(R.color.theme_status_bar);
        initView();
    }

    private void initView() {
        edt_user_id = (EditText) findViewById(R.id.edt_user_id);

    }


    public void performLogin(View view) {
        KeyboardUtils.getInstance().hideKeyboard(LoginActivity.this);
        if (isValid()) {
          if(!NetworkUtils.getInstance().isNetworkConnected(this)){
              Toast.makeText(getApplicationContext(),"Please check your connection",Toast.LENGTH_LONG).show();
              return;
          }
            final ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setMessage(getString(R.string.msg_progress_dialog));
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            final String uid = edt_user_id.getText().toString().trim();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("users").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            boolean isError = false;
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                DataSnapshot snapshot = postSnapshot.child("uid");
                                Log.w(getClass().getSimpleName(), snapshot.getValue().toString());

                                if (uid.equals("rahul")) {
                                    sessionManager.setLogin(true);
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    isError = true;
                                    break;
                                }
                            }
                            if(!isError){
                                Toast.makeText(LoginActivity.this, R.string.validation_login_auth_fail, Toast.LENGTH_SHORT).show();
                            }
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, R.string.validation_login_auth_fail, Toast.LENGTH_SHORT).show();
                        }
                    });
        }}

            /*if (FirebaseDatabaseManger.getInstance().checkLogin(edt_user_id.getText().toString().trim())) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(LoginActivity.this, R.string.validation_login_auth_fail, Toast.LENGTH_SHORT).show();
            }*/

 /*   public void performLogin(View view){


        if(TextUtils.isEmpty(edt_user_id.getText().toString())){

            Toast.makeText(getApplicationContext(),"Please enter correct details",Toast.LENGTH_LONG).show();
        }else{
            if(edt_user_id.getText().toString().equalsIgnoreCase("igdefault")){
                sessionManager.setLogin(true);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else{
                Toast.makeText(getApplicationContext(),"Please enter correct details",Toast.LENGTH_LONG).show();
            }
        }
    }*/

    private boolean isValid() {
        if (edt_user_id.getText().toString().trim().length() < 1) {
            Toast.makeText(LoginActivity.this, R.string.validation_message_login, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
