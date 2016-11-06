package com.geek.firstaid.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;
import com.geek.firstaid.activities.base.RegisterActivity;
import com.geek.firstaid.adapter.DoctorListAdapter;
import com.geek.firstaid.utilities.DoctorInfoBean;

import java.util.ArrayList;
import java.util.List;

public class DoctorInfoActivity extends AppBaseActivity {

    private List<DoctorInfoBean> doctorList = new ArrayList<>();
    private ListView listView;
    private DoctorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        setToolbar(getString(R.string.doctor), true);
        setStatusBarColorResource(R.color.theme_status_bar);
        doctorList.add(new DoctorInfoBean("Dr. Batra", "Delhi", "BHMS", "9889455621", "Hair Specialist", "15 years"));
        doctorList.add(new DoctorInfoBean("Dr. Puneet Rajput", "Delhi", "MBBS , DNB", "9889455621", "Dermatologist", "8 years"));
        doctorList.add(new DoctorInfoBean("Dr. Anil Kochar", "Faridabad", "BDS", "9882675621", "Dentist", "19 years"));
        doctorList.add(new DoctorInfoBean("Dr. D K Chhabra", "Raipur", "MBBS,  MS-Neuro Surgery", "9789455621", "Neurosurgeon", "45 years"));
        doctorList.add(new DoctorInfoBean("Dr. Akhil Agarwal", "Lucknow", "BDS, MDS", "9889400209", "Orthodontist ", "6 years"));
        doctorList.add(new DoctorInfoBean("Dr. Ritwiz Bihari", "Patna", "MBBS and MD, DM - Neurology", "9862055621", "Neurologist ", "7 years"));

        listView = (ListView) findViewById(R.id.list);
        adapter = new DoctorListAdapter(this, doctorList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + doctorList.get(i).getNumber()));
                if (ActivityCompat.checkSelfPermission(DoctorInfoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(DoctorInfoActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void onExitTap(View view)
    {
        Intent intent=new Intent(DoctorInfoActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
