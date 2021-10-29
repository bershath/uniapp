package org.bershath.labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
                        DBContract.DBDataEntry.COLUMN_NAME_EMAIL,
                        DBContract.DBDataEntry.COLUMN_NAME_USERNAME
                };
                // Filter results WHERE "title" = 'My Title'
                String selection = DBContract.DBDataEntry.COLUMN_NAME_EMAIL + " = ?"+" AND " + DBContract.DBDataEntry.COLUMN_NAME_PASSWORD +"=?";
                String[] selectionArgs = { userEmail,password };

                // How you want the results sorted in the resulting Cursor
                String sortOrder = DBContract.DBDataEntry.COLUMN_NAME_EMAIL + " DESC";

                Cursor cursor = db.query(
                        DBContract.DBDataEntry.USER_TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        sortOrder               // The sort order
                );

                boolean pos = cursor.moveToNext();
                final String vUname = cursor.getString(1);
                Bundle bundle = cursor.getExtras();
                String uname = bundle.getString("name");
                String sas = uname;


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