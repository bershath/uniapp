package org.bershath.labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        final Button button = findViewById(R.id.regSignIn);


        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        final String statusStr = extras.getString("DB_STATUS");
        final long status = Long.parseLong(statusStr);
        if(status >0 ){
            TextView userView = findViewById(R.id.regStatus);
            userView.setText("Registration Successful! user "+extras.getString("userName") +" registered !");
            userView = findViewById(R.id.regStatusTwo);
            userView.setText("Please sign in to continue");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Authenticate.class);
                startActivityForResult(i,4);
            }
        });


    }
}