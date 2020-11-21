package com.example.databasedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.databasedemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="UserInfo.db";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE "+UsersMaster.Users.TABLE_NAME+"("+UsersMaster.Users._ID+" INTEGER PRIMARY KEY,"
                                    +UsersMaster.Users.COLUMN_NAME_USERNAME+" TEXT,"
                                    +UsersMaster.Users.COLUMN_NAME_PASSWORD+" TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

        String SQL_TABLE_PRODUCT = "CREATE TABLE "+UsersMaster.Product.TABLE_NAME+"("+UsersMaster.Product._ID+" INTEGER PRIMARY KEY,"
                +UsersMaster.Product.COLUMN_NAME_PRODUCT_NAME+" TEXT,"
                +UsersMaster.Product.COLUMN_NAME_USER_ID+" INTEGER,"
                +"FOREIGN KEY("+
                UsersMaster.Product.COLUMN_NAME_USER_ID+") REFERENCES "+
                UsersMaster.Users.TABLE_NAME+"("+UsersMaster.Users._ID+"))";


        db.execSQL(SQL_TABLE_PRODUCT);


    }

    public boolean addInfo(User u){

        SQLiteDatabase db=getWritableDatabase();

        ContentValues value=new ContentValues();

        value.put(UsersMaster.Users.COLUMN_NAME_USERNAME,u.getUsername());
        value.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,u.getPassword());

        long newRowID=db.insert(UsersMaster.Users.TABLE_NAME,null,value);

        if (newRowID>=1){
            return true;
        }
        else {
            return false;
        }

    }
    public List readAllInfo(String req){

        SQLiteDatabase db=getReadableDatabase();

        String[] projection ={
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_USERNAME,
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UsersMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);
        }
        cursor.close();

        if(req=="user"){
            return userNames;
        }
        else if(req=="password"){
            return passwords;
        }
        else{
            return null;
        }

    }

    public void deleteInfo(String userName){

        SQLiteDatabase db=getReadableDatabase();

        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";

        String[] selectionArgs = { userName };

        db.delete(UsersMaster.Users.TABLE_NAME,selection,selectionArgs);
    }

    public void updateInfo(String userName,String password){

        SQLiteDatabase db=getReadableDatabase();

        ContentValues value=new ContentValues();
        value.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);

        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                value,
                selection,
                selectionArgs

        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
