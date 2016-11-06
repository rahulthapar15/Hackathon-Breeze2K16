package com.geek.firstaid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;

public class ChooseDiseaseActivity extends AppBaseActivity {

    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_disease);
        setToolbar(getString(R.string.title_activity_choose_disease), true);
        setStatusBarColorResource(R.color.theme_status_bar);
        setToolbarColorResource(R.color.theme_toolbar);
        mLinearLayout=(LinearLayout)findViewById(R.id.diseaseContainer);

        String[] listname=getResources().getStringArray(R.array.diseases);



        for(int i=0;i<listname.length;i++){

            final Button button=new Button(this);
            button.setText(listname[i]);
            button.setBackgroundResource(R.drawable.button_bg_blue);
            button.setTextColor(Color.WHITE);
            button.setId(i);
            button.setTag(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);
            button.setLayoutParams(params);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ChooseDiseaseActivity.this,SecondDignosticActivity.class);
                    intent.putExtra("val",Integer.parseInt(button.getTag().toString()));
                    startActivity(intent);
                }
            });
            mLinearLayout.addView(button);
        }
        /*ImageView icon=(ImageView)findViewById(R.id.gender_icon);
        icon.setImageResource(new SessionManager(this).getGender()?R.drawable.female:R.drawable.male);
*/



    }
}
