package com.example.shalini.sqliteexample;

/**
 * Created by Shalini on 13-01-2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM";
    public static final String COL_3 = "COMPANY";
    public static final String COL_4 = "QUANTITY";
    public static final String COL_5 = "WHP";
    public static final String COL_6 = "SP";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEM TEXT,COMPANY TEXT,QUANTITY INTEGER, WHP INTEGER, SP INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String item,String company,String quantity, String whp, String sp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,company);
        contentValues.put(COL_4,quantity);
        contentValues.put(COL_5,whp);
        contentValues.put(COL_6,sp);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public boolean updateData(String id,String item,String company,String quantity, String whp, String sp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,company);
        contentValues.put(COL_4,quantity);
        contentValues.put(COL_5,whp);
        contentValues.put(COL_6,sp);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
 /*  public Cursor finditem(String itemname){
        SQLiteDatabase db = this.getWritableDatabase();
       String qry= itemname;
        //Cursor cursor=db.rawQuery( "select * from " + TABLE_NAME + "where ITEM=?",new String[ ] { item });
       Cursor cursor= db.rawQuery("select * from " + TABLE_NAME + " WHERE item='" + qry, null);
       if(cursor != null)
       {
           cursor.moveToFirst();
       }
       return cursor;
    }
*/
}

