package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasedemo.database.DbHelper;
import com.example.databasedemo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    EditText etName,etpwd;
    Button btnAdd,btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DbHelper(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnSelect = findViewById(R.id.btnSelect);
        etName=findViewById(R.id.etName);
        etpwd=findViewById(R.id.Password);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u=etName.getText().toString();
                String p=etpwd.getText().toString();

                User user_obj=new User(u,p);

                dbHelper.addInfo(user_obj);




            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List unames = dbHelper.readAllInfo("user");
                Toast.makeText(MainActivity.this,unames.toString(),Toast.LENGTH_SHORT).show();

            }
        });



    }
    //using onclick in xml file

    public void deleteData(View view){
        DbHelper dbHelper = new DbHelper(this);

        dbHelper.deleteInfo(etName.getText().toString());

        Toast.makeText(this,etName.getText().toString()+" deleted successfully",Toast.LENGTH_SHORT).show();
    }

    public void updateInfo(View view){
        DbHelper dbHelper = new DbHelper(this);

        dbHelper.updateInfo(etName.getText().toString(),etpwd.getText().toString());

        Toast.makeText(this,etName.getText().toString()+" updated successfully",Toast.LENGTH_SHORT).show();
    }

    public void signIn(View view){

        DbHelper dbHelper = new DbHelper(this);

        List unames = dbHelper.readAllInfo("user");
        List passwords = dbHelper.readAllInfo("password");

        String user = etName.getText().toString();
        String password = etpwd.getText().toString();

        if(unames.indexOf(user)>=0){
            if(passwords.get(unames.indexOf(user)).equals(password)){
                Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "login unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
