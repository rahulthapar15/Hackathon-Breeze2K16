package com.geek.firstaid.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.base.AppBaseActivity;
import com.geek.firstaid.activities.base.BaseActivity;
import com.geek.firstaid.activities.base.RegisterActivity;
import com.geek.firstaid.adapter.MyPrintDocumentAdapter;
import com.geek.firstaid.database.DatabaseHandler;
import com.geek.firstaid.utilities.PatientInfoBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrescriptionActivity extends AppBaseActivity {

    private StringBuilder sb;
    private TextView textPrescrption;
    private TextView pName;
    private TextView age;
    private TextView sex;
    private PatientInfoBean infoBean;
    private StringBuilder temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        setToolbar(getString(R.string.title_activity_prescription), true);
        setStatusBarColorResource(R.color.theme_status_bar);
        textPrescrption=(TextView)findViewById(R.id.text_report);
        pName = (TextView) findViewById(R.id.text_pname);
        age=(TextView)findViewById(R.id.text_page);
        sex=(TextView)findViewById(R.id.text_psex);

        DatabaseHandler handler=new DatabaseHandler(PrescriptionActivity.this);

        infoBean=handler.getLastRecord();
        pName.setText(getResources().getString(R.string.patient_name)+" " +infoBean.getName());
        age.setText(getResources().getString(R.string.age)+"  : " +infoBean.getAge());
        sex.setText(getResources().getString(R.string.sex)+"  : " +infoBean.getSex());

        int index = Integer.parseInt(getIntent().getStringExtra("key"));

        String name[] = new String[0];
        switch (index) {
            case 0:
                name = getResources().getStringArray(R.array.drug_fever);
                break;
            case 1:
                name = getResources().getStringArray(R.array.drug_body_pain);
                break;
            case 2:
                name = getResources().getStringArray(R.array.drug_body_cough);
                break;
            case 3:
                name = getResources().getStringArray(R.array.drug_headache);
                break;
            case 4:
                name = getResources().getStringArray(R.array.drug_pneumonia);
                break;
            default:
                name = getResources().getStringArray(R.array.drug_pneumonia);
                break;
        }


        sb=new StringBuilder();
        sb.append("Patient Name :"+infoBean.getName());
        sb.append("\n");
        sb.append("Age :"+infoBean.getAge());
        sb.append("\n");
        sb.append("Sex : "+infoBean.getSex());
        sb.append("\n");
        for(int i=0;i<name.length;i++)
        {
            sb.append(name[i]+"    - "+getString(R.string.qty)+"  : "+getResources().getString(R.string.day_time));
            sb.append("\n");
            sb.append("\n");
        }


        temp=new StringBuilder();

        for(int i=0;i<name.length;i++)
        {
            temp.append(name[i]+"    - "+getString(R.string.qty)+"  : "+getResources().getString(R.string.day_time));
            temp.append("\n");
            temp.append("\n");
        }

        textPrescrption.setText(temp.toString());

    }

    public void onPrint(View view)
    {
        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = this.getString(R.string.app_name) +
                sb.toString();

        printManager.print(jobName, new MyPrintDocumentAdapter(this),
                null);

    }
    public void onDownload(View view)
    {

      try
        {

            Calendar c = Calendar.getInstance();

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());

            StringBuilder builder=new StringBuilder();

            builder.append("***********REPORT****************");
            builder.append("\n");
            builder.append("Date : "+formattedDate);
            builder.append("\n");
            builder.append("*************************");

            builder.append("\n");
            builder.append(sb.toString());
            builder.append("\n");


            builder.append("*************************");




            String pdfName=""+ c.getTime();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(Environment.getExternalStorageDirectory() + "/"+pdfName+".pdf"));
            document.open();
            document.add(new Paragraph(builder.toString()));
            document.close();

            Toast.makeText(PrescriptionActivity.this,getResources().getString(R.string.save),Toast.LENGTH_LONG).show();
            Log.d("OK", "done");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onExitTap(View view)
    {
        Intent intent=new Intent(PrescriptionActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PrescriptionActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
