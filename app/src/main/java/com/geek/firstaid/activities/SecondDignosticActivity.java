package com.geek.firstaid.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;
import com.geek.firstaid.utilities.MyDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class SecondDignosticActivity extends AppBaseActivity {

    private List<CheckBox> checkBoxes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_dignostic);
        setStatusBarColorResource(R.color.theme_status_bar);
        setToolbar(getString(R.string.title_ativity_second_diagnostic), true);

        String name[] = new String[0];


        LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.seconddiseaseContainer);

        switch (getIntent().getIntExtra("val", 0)) {
            case 0:
                name = getResources().getStringArray(R.array.symptoms_fever);
                break;
            case 1:
                name = getResources().getStringArray(R.array.symptoms_pneumonia);
                break;
            case 2:
                name = getResources().getStringArray(R.array.symptoms_headache);
                break;
            case 3:
                name = getResources().getStringArray(R.array.symptoms_body_pain);
                break;
            case 4:
                name = getResources().getStringArray(R.array.symptoms_cough);
                break;
            case 5:
                name = getResources().getStringArray(R.array.symptoms_mouth_ulcer);
                break;
            case 6:
                name = getResources().getStringArray(R.array.symptoms_stomach_infection);
                break;
            case 7:
                name = getResources().getStringArray(R.array.symptoms_throat_infection);
                break;
            case 8:
                name = getResources().getStringArray(R.array.symptoms_body_rashes);
                break;
            case 9:
                name = getResources().getStringArray(R.array.symptoms_allergies);
                break;
            case 10:
                name = getResources().getStringArray(R.array.symptoms_constipation);
                break;

        }

        for (int i = 0; i < name.length; i++) {
            CheckBox radioButton = (CheckBox)((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.checkbox,null);
            radioButton.setText(name[i]);
            radioButton.setTag(i);
            radioButton.setPadding(4, 4, 4, 4);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setTextSize(15);
            checkBoxes.add(radioButton);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);
            radioButton.setLayoutParams(params);
            radioButton.setPadding(15,15,15,15);
            radioButton.setBackground( getResources().getDrawable(R.drawable.button_bg_blue));
            mLinearLayout.addView(radioButton);



        }
        /*ImageView icon=(ImageView)findViewById(R.id.gender_icon);
        icon.setImageResource(new SessionManager(this).getGender()?R.drawable.female:R.drawable.male);
*/
    }
    public void onPrescription(View v){
        int temp = checkBoxes.size();
        int checkCount = 0;
        for (int i = 0; i < temp; i++) {
            if (checkBoxes.get(i).isChecked()) {
                checkCount++;
            }

        }
        if (temp == checkCount) {
            FragmentManager fm = getFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment();
            dialogFragment.setCancelable(false);
            dialogFragment.show(fm, "Sample Fragment");
            return;

        }
        if(checkCount==0) {
            return;
        }
        else
        {
            startActivity(new Intent(SecondDignosticActivity.this, PrescriptionActivity.class).putExtra("key", checkBoxes.get(checkCount).getTag().toString()));
        }

    }
}
