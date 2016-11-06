package com.geek.firstaid.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;
import com.geek.firstaid.activities.base.RegisterActivity;
import com.geek.firstaid.database.DatabaseHandler;
import com.geek.firstaid.manager.FirebaseDatabaseManger;
import com.geek.firstaid.utilities.NetworkUtils;
import com.geek.firstaid.utilities.PatientInfoBean;
import com.geek.firstaid.utilities.SessionManager;

import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppBaseActivity {

    private SessionManager mSessionManager;
    private Button mSpinner;
    private String mPlaceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        setToolbar(getString(R.string.title_activity_home));
        setStatusBarColorResource(R.color.theme_status_bar);
//        setToolbarColorResource(R.color.theme_toolbar);
        mSessionManager=new SessionManager(this);
        mSpinner=(Button)findViewById(R.id.button_place);
        mSpinner.setOnClickListener(onClickListener);

        if(NetworkUtils.getInstance().isNetworkAvailable(HomeActivity.this)){
            DatabaseHandler handler = new DatabaseHandler(HomeActivity.this);
            List<PatientInfoBean> infoBeen=handler.getAllContacts();
            FirebaseDatabaseManger.getInstance().writeMapToDb(infoBeen);
        }
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          /*  PopupMenu popupMenu = new PopupMenu(HomeActivity.this, v);
            popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
            popupMenu.inflate(R.menu.place);
            popupMenu.show();*/

            final String [] place=getResources().getStringArray(R.array.place_name);


            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("");
            builder.setItems(place, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    // Do something with the selection
                    mSpinner.setText(place[item]);
                    mPlaceName=place[item];
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };

    PopupMenu.OnMenuItemClickListener onMenuItemClickListener=new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            mSpinner.setText(mPlaceName);
            return false;
        }
    };

    public void onEnglishTap(View view){
        mSessionManager.saveLanguage(true);
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        launchRegister();
    }

    private void launchRegister() {
        if(TextUtils.isEmpty(mPlaceName)){
            Toast.makeText(this, R.string.place_error_msg,Toast.LENGTH_LONG).show();
            return;
        }
        mSessionManager.savePlace(mPlaceName);
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onHindiTap(View view){
        mSessionManager.saveLanguage(true);
        Locale locale = new Locale("hi");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        launchRegister();
    }
}
