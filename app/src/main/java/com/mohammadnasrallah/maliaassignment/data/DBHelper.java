package com.mohammadnasrallah.maliaassignment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 4;
    private static DBHelper instance;

    // Menu table
    public static final String TABLE_MENUS = "menus";
    public static final String COLUMN_MENU_ID = "id";
    public static final String COLUMN_MENU_TITLE = "title";

    // Customer table
    public static final String TABLE_CUSTOMERS = "customers";
    public static final String COLUMN_CUSTOMER_ID = "id";
    public static final String COLUMN_CUSTOMER_NAME = "name";

    // Invoice table
    public static final String TABLE_INVOICES = "invoices";
    public static final String COLUMN_INVOICE_ID = "id";
    public static final String COLUMN_INVOICE_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_INVOICE_AMOUNT = "amount";
    public static final String COLUMN_INVOICE_DATE = "date";

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the menus table
        String CREATE_MENUS_TABLE = "CREATE TABLE " + TABLE_MENUS + " ("
                + COLUMN_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MENU_TITLE + " TEXT);";
        db.execSQL(CREATE_MENUS_TABLE);

        // Insert dummy data for menus
        db.execSQL("INSERT INTO " + TABLE_MENUS + " (" + COLUMN_MENU_TITLE + ") VALUES ('Customers')");

        // Create the customers table
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + " ("
                + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CUSTOMER_NAME + " TEXT);";
        db.execSQL(CREATE_CUSTOMERS_TABLE);

        // Insert dummy data for customers
        for (int i = 1; i <= 5; i++) {
            db.execSQL("INSERT INTO " + TABLE_CUSTOMERS + " (" + COLUMN_CUSTOMER_NAME + ") VALUES ('Customer " + i + "')");
        }

        // Create the invoices table
        String CREATE_INVOICES_TABLE = "CREATE TABLE " + TABLE_INVOICES + " ("
                + COLUMN_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_INVOICE_CUSTOMER_ID + " INTEGER, "
                + COLUMN_INVOICE_AMOUNT + " REAL, "
                + COLUMN_INVOICE_DATE + " TEXT);";
        db.execSQL(CREATE_INVOICES_TABLE);

        // Insert dummy data for invoices
        for (int customerId = 1; customerId <= 5; customerId++) {
            for (int invoiceId = 1; invoiceId <= 3; invoiceId++) {
                db.execSQL("INSERT INTO " + TABLE_INVOICES + " (" + COLUMN_INVOICE_CUSTOMER_ID + ", " + COLUMN_INVOICE_AMOUNT + ", " + COLUMN_INVOICE_DATE + ") VALUES (" + customerId + ", " + (invoiceId * 100) + ", '2023-01-" + (invoiceId + customerId) + "')");
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICES);
        onCreate(db);
    }
}

