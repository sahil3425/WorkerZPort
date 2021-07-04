package com.example.workerzport.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.workerzport.MainActivity;
import com.example.workerzport.R;

public class SendMailActivity extends AppCompatActivity {
    EditText editTextTo,editTextSubject,editTextMessage;
    Button send;
    String Mail="";
    String Message="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        editTextTo=(EditText)findViewById(R.id.editText1);
        editTextSubject=(EditText)findViewById(R.id.editText2);
        editTextMessage=(EditText)findViewById(R.id.editText3);
        Mail=getIntent().getStringExtra("MailAddress");
        Message=getIntent().getStringExtra("CompanyName");
        editTextTo.setText(Mail.substring(Mail.indexOf(":")+2));
        editTextMessage.setText(Message.substring(Message.indexOf(":")+2));
        send=(Button)findViewById(R.id.button1);

        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String subject=editTextSubject.getText().toString();
                String to=editTextTo.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{ to} );
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("CompanyName"));

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }

        });
    }
//        Log.i("Mail",getIntent().getStringExtra("MailAddress"));

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1 = new Intent(getApplicationContext(), development.class);
        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
    }
//        Log.i("Mail",getIntent().getStringExtra("CompanyName"));

    }
