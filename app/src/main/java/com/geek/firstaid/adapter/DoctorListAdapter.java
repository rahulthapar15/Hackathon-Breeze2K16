package com.geek.firstaid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geek.firstaid.R;
import com.geek.firstaid.utilities.DoctorInfoBean;

import java.util.List;

public class DoctorListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DoctorInfoBean> doctorList;

    public DoctorListAdapter(Activity activity, List<DoctorInfoBean> doctorList) {
        this.activity = activity;
        this.doctorList = doctorList;
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public DoctorInfoBean getItem(int location) {
        return doctorList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.doctor_list_row, null);


        TextView name = (TextView) convertView.findViewById(R.id.txt_doctor_name);
        TextView degree = (TextView) convertView.findViewById(R.id.txt_qualification);
        TextView city = (TextView) convertView.findViewById(R.id.txt_city);
        TextView specialization = (TextView) convertView.findViewById(R.id.txt_specialization);
        TextView number = (TextView) convertView.findViewById(R.id.txt_number);
        TextView experience = (TextView) convertView.findViewById(R.id.txt_experience);


        name.setText("Name :"+doctorList.get(position).getName());
        degree.setText(doctorList.get(position).getDegree());
        city.setText(doctorList.get(position).getCity());
        specialization.setText(doctorList.get(position).getSpecialization());
        number.setText(doctorList.get(position).getNumber());
        experience.setText(doctorList.get(position).getExperience());


        return convertView;
    }

}

