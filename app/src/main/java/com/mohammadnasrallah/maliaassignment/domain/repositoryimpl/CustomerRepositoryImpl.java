package com.mohammadnasrallah.maliaassignment.domain.repositoryimpl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mohammadnasrallah.maliaassignment.domain.repository.CustomerRepository;
import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private DBHelper dbHelper;

    public CustomerRepositoryImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    @Override
    public List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_CUSTOMERS,
                new String[]{DBHelper.COLUMN_CUSTOMER_ID, DBHelper.COLUMN_CUSTOMER_NAME},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_CUSTOMER_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_CUSTOMER_NAME));

                customers.add(new Customer(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return customers;
    }
}
