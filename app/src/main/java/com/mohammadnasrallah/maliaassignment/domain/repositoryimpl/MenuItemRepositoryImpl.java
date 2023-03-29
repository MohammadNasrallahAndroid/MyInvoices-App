package com.mohammadnasrallah.maliaassignment.domain.repositoryimpl;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.domain.MenuItem;
import com.mohammadnasrallah.maliaassignment.domain.repository.MenuItemRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository {

    private DBHelper dbHelper;

    public MenuItemRepositoryImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    // get the list of menu items from the database
    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_MENUS,
                new String[]{DBHelper.COLUMN_MENU_ID, DBHelper.COLUMN_MENU_TITLE},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_MENU_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_MENU_TITLE));
                menuItems.add(new MenuItem(id, title));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return menuItems;
    }
}


