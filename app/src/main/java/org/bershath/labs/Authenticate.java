package org.bershath.labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.bershath.labs.data.db.DBContract;

public class Authenticate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        final Button authenticate = findViewById(R.id.authenticate);

        final Button button = findViewById(R.id.newRegistration);

        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText userEmailET = findViewById(R.id.authEmail);
                final String userEmail = userEmailET.getText().toString();

                final EditText userPasswordET = findViewById(R.id.authPassword);
                final String password = userPasswordET.getText().toString();

                DBContract.DBHelper dbHelper = new DBContract.DBHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String[] projection = {
                        BaseColumns._ID,
                        DBContract.DBDataEntry.COLUMN_NAME_EMAIL,
                        DBContract.DBDataEntry.COLUMN_NAME_USERNAME
                };


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterUsers.class);
                startActivityForResult(i,3);
            }
        });



    }
}