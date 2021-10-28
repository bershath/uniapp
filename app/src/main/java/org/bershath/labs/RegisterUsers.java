package org.bershath.labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.bershath.labs.data.db.DBContract;

public class RegisterUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_users);
        final Button button = findViewById(R.id.registerNow);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText userNameET = findViewById(R.id.glRegUserName);
                final String userName = userNameET.getText().toString();
                final EditText ageET = findViewById(R.id.glRegAge);
                final int age = Integer.parseInt(ageET.getText().toString()) ;
                //final int age =32;
                final EditText localizationET = findViewById(R.id.glRegLocalization);
                final String localization = localizationET.getText().toString();
                final EditText emailET = findViewById(R.id.glRegEmail);
                final String email = emailET.getText().toString();
                final EditText passwordET = findViewById(R.id.glRegPassword);
                final String password = passwordET.getText().toString();
                char tempOldPerson = 'Y';
                if(age < 50){
                    tempOldPerson = 'N';
                }
                final char oldPerson = tempOldPerson;


                DBContract.DBHelper dbHelper = new DBContract.DBHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DBContract.DBDataEntry.COLUMN_NAME_EMAIL,email);
                values.put(DBContract.DBDataEntry.COLUMN_NAME_USERNAME,userName);
                values.put(DBContract.DBDataEntry.COLUMN_NAME_AGE,age);
                values.put(DBContract.DBDataEntry.COLUMN_NAME_LOCALIZATION,localization);
                values.put(DBContract.DBDataEntry.COLUMN_NAME_PASSWORD,password);
                values.put(DBContract.DBDataEntry.COLUMN_NAME_YOUNG_OLD, Character.toString(oldPerson));
                long newRowId = db.insert(DBContract.DBDataEntry.USER_TABLE_NAME, null, values);

                Intent i = new Intent(getApplicationContext(),RegisterSuccess.class);
                i.putExtra("userName",userName);
                i.putExtra("age", age);
                i.putExtra("localization",localization);
                i.putExtra("email",email);
                i.putExtra("DB_STATUS",String.valueOf(newRowId));
                startActivityForResult(i,3);

            }
        });



    }
}