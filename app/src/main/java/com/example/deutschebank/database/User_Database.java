package com.example.deutschebank.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.deutschebank.model.History;
import com.example.deutschebank.model.User;
import com.example.deutschebank.sql_table.UserTable;

import java.util.ArrayList;
import java.util.List;

public class User_Database extends SQLiteOpenHelper {
    public User_Database(Context context) {
        super(context,UserTable.DB_NAME, null, UserTable.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create1= "CREATE TABLE "+ UserTable.TABLE_NAME+"("
                +UserTable.ID+" INTEGER PRIMARY KEY,"+
                UserTable.HOLDER_NAME+" TEXT," +
                UserTable.ACCOUNT_NUMBER+" INTEGER,"+
                UserTable.IFSC_CODE+" TEXT"+
                ")";
        Log.d("dbAMAN","Querry being run is : "+create1);
        sqLiteDatabase.execSQL(create1);

        String create2= "CREATE TABLE HISTORY"+"("
                +UserTable.ID+" INTEGER PRIMARY KEY,"+
                UserTable.HOLDER_NAME+" TEXT," +
                UserTable.ACCOUNT_NUMBER+" INTEGER,"+
                "balance INTEGER"+
                ")";
        Log.d("dbAMAN","Querry being run is : "+create2);
        sqLiteDatabase.execSQL(create2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add_user_detail(User user){
        SQLiteDatabase liteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.ID,user.getClient_id());
        values.put(UserTable.HOLDER_NAME,user.getHolder_name());
        values.put(UserTable.ACCOUNT_NUMBER,user.getAccount_number());
        values.put(UserTable.IFSC_CODE,user.getIfsc_code());
        liteDatabase.insert(UserTable.TABLE_NAME,null,values);
        Log.d("dbAMAN","INSERT SUCCESSFULLY");
        liteDatabase.close();
    }

    public List<User> get_user_list(){
        List<User> userList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String select2 = "SELECT * FROM "+UserTable.TABLE_NAME ;
        Cursor cursor1 = sqLiteDatabase.rawQuery(select2,null);

        if (cursor1.moveToFirst()){
            do {
                User uc1 = new User();
                uc1.setClient_id(Integer.parseInt(cursor1.getString(0)));
                uc1.setHolder_name(cursor1.getString(1));
                uc1.setAccount_number(Integer.parseInt(cursor1.getString(2)));
                uc1.setIfsc_code(cursor1.getString(3));
                userList.add(uc1);
            }
            while (cursor1.moveToNext());
        }
        return userList;
    }

    public List<User> get_bc_list(int position){
        List<User> bc_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select3 = "SELECT * FROM "+UserTable.TABLE_NAME+" WHERE "+UserTable.ID+" = "+position;
        Cursor cursor2= db.rawQuery(select3,null);
        if (cursor2.moveToFirst()){
            do {
                User uc2 = new User();
                uc2.setClient_id(Integer.parseInt(cursor2.getString(0)));
                uc2.setHolder_name(cursor2.getString(1));
                uc2.setAccount_number(Integer.parseInt(cursor2.getString(2)));
                uc2.setIfsc_code(cursor2.getString(3));
                bc_list.add(uc2);
            }while (cursor2.moveToNext());
        }
        return bc_list;
    }

    public void add_list(History history){
        SQLiteDatabase liteDatabase1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.ID,history.getClient_id() );
        values.put(UserTable.HOLDER_NAME,history.getHolder_name());
        values.put(UserTable.ACCOUNT_NUMBER,history.getAccount_number());
        values.put(UserTable.BALANCE,history.getBalance());
        liteDatabase1.insert("HISTORY",null,values);
        Log.d("dbAMAN","INSERT SUCCESSFULLY");
        liteDatabase1.close();
    }

    public List<User> get_list(int posi){
        Log.d("q","list : "+posi);
        List<User> hs_list = new ArrayList<>();
        SQLiteDatabase db1 = this.getReadableDatabase();
        String select4 = "SELECT "+ UserTable.ID +", "+UserTable.HOLDER_NAME+", "+UserTable.ACCOUNT_NUMBER
                +" FROM "+UserTable.TABLE_NAME+" WHERE "+UserTable.ID+" = "+posi;
        Cursor cursor3= db1.rawQuery(select4,null);
        if (cursor3.moveToFirst()){
            do {
                User uc3 = new User();
                uc3.setClient_id(Integer.parseInt(cursor3.getString(0)));
                uc3.setHolder_name(cursor3.getString(1));
                uc3.setAccount_number(Integer.parseInt(cursor3.getString(2)));
                hs_list.add(uc3);
            }while (cursor3.moveToNext());
        }
        return hs_list;
    }


    public List<History> get_hlist(){
        List<History> hs1_list = new ArrayList<>();
        SQLiteDatabase db2= this.getReadableDatabase();
        String select4 = "SELECT * FROM HISTORY";
        Cursor cursor4= db2.rawQuery(select4,null);
        if (cursor4.moveToFirst()){
            do {

                History uc4 = new History();
                uc4.setClient_id(Integer.parseInt(cursor4.getString(0)));
                uc4.setHolder_name(cursor4.getString(1));
                uc4.setAccount_number(Integer.parseInt(cursor4.getString(2)));
                uc4.setBalance(Integer.parseInt(cursor4.getString(3)));
                hs1_list.add(uc4);
            }while (cursor4.moveToNext());
        }
        return hs1_list;
    }


}
