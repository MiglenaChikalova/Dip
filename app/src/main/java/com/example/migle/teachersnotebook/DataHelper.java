package com.example.migle.teachersnotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ContextMenu;

import static android.os.Build.ID;
import static java.sql.Types.NULL;
import static java.sql.Types.VARCHAR;

public class DataHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "gradebook.sqlite";
    private static  final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Course";
    public static final String COL_1 = "id";
    public static final String COL_2 = "course_title";

    public  DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase database){
        database.execSQL("select * from " + TABLE_NAME);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

     public  boolean insertData(String title){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        long result = database.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getCourseData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }
}
