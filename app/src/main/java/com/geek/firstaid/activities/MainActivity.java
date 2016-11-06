package com.geek.firstaid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;
import com.geek.firstaid.activities.base.BaseActivity;

public class MainActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar("Test");
        setNavigationBarColor("#570710");
        setStatusBarColor("#570710");
        setToolbarTitleColor("#570710");
        setToolbarColor("#000000");
    }
}
