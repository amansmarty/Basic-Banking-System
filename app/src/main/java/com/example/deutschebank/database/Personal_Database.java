package com.example.deutschebank.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.deutschebank.model.Personal;
import com.example.deutschebank.sql_table.MyTable;
import java.util.ArrayList;
import java.util.List;

public class Personal_Database extends SQLiteOpenHelper {
    public Personal_Database(Context context) {
        super(context, MyTable.DB_NAME, null, MyTable.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create = "CREATE TABLE "+MyTable.TABLE_NAME+"("
                +MyTable.ID+" INTEGER,"+
                MyTable.HOLDER_NAME+" TEXT," +
                MyTable.ACCOUNT_NUMBER+" INTEGER PRIMARY KEY,"+
                MyTable.IFSC_CODE+" TEXT,"+
                MyTable.MOBILE+" TEXT,"+
                MyTable.BALANCE+" INTEGER"+
                ")";
        Log.d("dbAMAN","Querry being run is : "+create);
        sqLiteDatabase.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add_personal_details(Personal personal){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyTable.ID,personal.getClient_id());
        values.put(MyTable.HOLDER_NAME,personal.getHolder_name());
        values.put(MyTable.ACCOUNT_NUMBER,personal.getAccount_number());
        values.put(MyTable.IFSC_CODE,personal.getIfsc_code());
        values.put(MyTable.MOBILE,personal.getMobile());
        values.put(MyTable.BALANCE,personal.getBalance());

        database.insert(MyTable.TABLE_NAME,null,values);
        Log.d("dbAMAN","Insert Successfully");
        database.close();
    }

    public List<Personal> get_personal_list() {
        List<Personal> personalList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select1 = "SELECT * FROM " + MyTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(select1,null);

        if(cursor.moveToFirst())
        {
            do {
                Personal personal1 = new Personal();
                personal1.setClient_id(Integer.parseInt(cursor.getString(0)));
                personal1.setHolder_name(cursor.getString(1));
                personal1.setAccount_number(Integer.parseInt(cursor.getString(2)));
                personal1.setIfsc_code(cursor.getString(3));
                personal1.setMobile(cursor.getString(4));
                personal1.setBalance(Integer.parseInt(cursor.getString(5)));
                personalList.add(personal1);
            }
            while (cursor.moveToNext());
        }
        return personalList;
    }


    public  int updateContact(Personal personal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(MyTable.BALANCE,personal.getBalance());

        //lets update now
        return db.update(MyTable.TABLE_NAME,values,MyTable.ID+"=?",
                new String[]{String.valueOf(personal.getClient_id())});

    }
}
