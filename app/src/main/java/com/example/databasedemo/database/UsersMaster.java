package com.example.databasedemo.database;

import android.provider.BaseColumns;

public class UsersMaster {
    private UsersMaster(){}

    public static class Users implements BaseColumns {

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }

    public static class Product implements BaseColumns{

        public static final String TABLE_NAME = "products";
        public static final String COLUMN_NAME_PRODUCT_NAME = "productName";
        public static final String COLUMN_NAME_USER_ID = "userProduct";

    }

}
