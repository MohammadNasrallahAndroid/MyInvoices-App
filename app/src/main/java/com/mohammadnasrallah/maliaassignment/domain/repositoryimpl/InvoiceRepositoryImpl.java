package com.mohammadnasrallah.maliaassignment.domain.repositoryimpl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;
import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.domain.Invoice;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class InvoiceRepositoryImpl implements InvoiceRepository {
    private DBHelper dbHelper;
    private MutableLiveData<Boolean> invoiceAddedObservable = new MutableLiveData<>();

    public InvoiceRepositoryImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    @Override
    public MutableLiveData<List<Invoice>> getInvoiceList(int customerId) {
        MutableLiveData<List<Invoice>> invoicesLiveData = new MutableLiveData<>();
        List<Invoice> invoices = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = DBHelper.COLUMN_INVOICE_CUSTOMER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(customerId)};

        Cursor cursor = db.query(DBHelper.TABLE_INVOICES,
                new String[]{DBHelper.COLUMN_INVOICE_ID, DBHelper.COLUMN_INVOICE_CUSTOMER_ID, DBHelper.COLUMN_INVOICE_AMOUNT, DBHelper.COLUMN_INVOICE_DATE},
                selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_ID));
                int customer_Id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_CUSTOMER_ID));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_AMOUNT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_DATE));

                invoices.add(new Invoice(id, customer_Id, amount, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        invoicesLiveData.setValue(invoices);
        return invoicesLiveData;
    }

    @Override
    public Boolean saveInvoice(double amount, String date, int customerId) {
         Invoice invoice = new Invoice(customerId, amount, date);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_INVOICE_CUSTOMER_ID, invoice.getCustomerId());
        contentValues.put(DBHelper.COLUMN_INVOICE_AMOUNT, invoice.getAmount());
        contentValues.put(DBHelper.COLUMN_INVOICE_DATE, invoice.getDate());

        long newRowId = db.insert(DBHelper.TABLE_INVOICES, null, contentValues);
        db.close();

        boolean success = newRowId != -1;
        invoiceAddedObservable.setValue(success);
        return success;
    }
    @Override
    public void refreshInvoiceList(MutableLiveData<List<Invoice>> invoicesLiveData, int customerId) {
        List<Invoice> invoices = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = DBHelper.COLUMN_INVOICE_CUSTOMER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(customerId)};

        Cursor cursor = db.query(DBHelper.TABLE_INVOICES,
                new String[]{DBHelper.COLUMN_INVOICE_ID, DBHelper.COLUMN_INVOICE_CUSTOMER_ID, DBHelper.COLUMN_INVOICE_AMOUNT, DBHelper.COLUMN_INVOICE_DATE},
                selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_ID));
                int customer_Id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_CUSTOMER_ID));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_AMOUNT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_INVOICE_DATE));

                invoices.add(new Invoice(id, customer_Id, amount, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        invoicesLiveData.setValue(invoices);
    }

}
