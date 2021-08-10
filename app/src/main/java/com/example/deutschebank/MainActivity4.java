package com.example.deutschebank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.deutschebank.database.User_Database;
import com.example.deutschebank.model.History;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
ListView listView;
    Toolbar toolbar_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView=findViewById(R.id.lv2);

        User_Database db1=new User_Database(MainActivity4.this);
        ArrayList<String> bf_historylist = new ArrayList<>();

        List<History> history_list = db1.get_hlist();
        for (History uc1:history_list){
            bf_historylist.add("\n"+"Client ID : "+uc1.getClient_id()+"\n"+
                    "Holder Name : "+uc1.getHolder_name()+ "\n"+
                    "Account Number : "+uc1.getAccount_number()+"\n"+
                    "Send $"+uc1.getBalance()+"\n");
        }



        ArrayAdapter<String> arrayAdapter34 = new ArrayAdapter<>(MainActivity4.this, android.R.layout.simple_list_item_1,bf_historylist);
        listView.setAdapter(arrayAdapter34);


        toolbar_4= findViewById(R.id.toolbar4);
        toolbar_4.setTitle("History");
        setSupportActionBar(toolbar_4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.menu_home){
            Toast.makeText(MainActivity4.this,"Home ",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity4.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.menu_history){
        }
        else if (id==R.id.menu_soon){
            Toast.makeText(MainActivity4.this,"Coming Soon!!!",Toast.LENGTH_SHORT).show();

        }
        else if (id==R.id.menu_exit) {
            new AlertDialog.Builder(MainActivity4.this).setIcon(R.drawable.ic_baseline_warning_24)
                    .setTitle("Exit")
                    .setMessage("are you sure want to exit")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();

                        }
                    }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity4.this, "Tap yes to exit from this app", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
        return true;
    }

}