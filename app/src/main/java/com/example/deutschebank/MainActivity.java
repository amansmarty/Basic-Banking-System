package com.example.deutschebank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deutschebank.database.Personal_Database;
import com.example.deutschebank.model.Personal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listView1;
Button button;
int cur_bal;
Toolbar toolbar_3;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1 = findViewById(R.id.mydetails_lv);

        Personal_Database personal_database = new Personal_Database(MainActivity.this);
        Personal ps= new Personal();

        ps.setClient_id(101);
        ps.setHolder_name("AMAN RAJ");
        ps.setAccount_number(07566);
        ps.setIfsc_code("SBI0007566");
        ps.setMobile("9122867035");
        ps.setBalance(45000);

        personal_database.add_personal_details(ps);


        ArrayList<String> ps_arraylist = new ArrayList<>();
        List<Personal> ps_list = personal_database.get_personal_list();

        for (Personal personal:ps_list){
            Log.d("dbAMAN","Holder Name : "+personal.getHolder_name()+"\n"+
                    "Account number : " +personal.getAccount_number()+"\n"+
                    "IFSC CODE : "+personal.getIfsc_code()+"\n"+
                    "Mobile : "+personal.getMobile()+"\n"+
                    "Balance : "+personal.getBalance()+"\n");


            ps_arraylist.add("\n\n\n\n"+"Client ID : "+personal.getClient_id()+"\n\n"+
                            "Holder Name : "+personal.getHolder_name()+ "\n\n"+
                            "Account Number : "+personal.getAccount_number()+"\n\n"+
                            "IFSC CODE : "+personal.getIfsc_code()+"\n\n"+
                            "Mobile : "+personal.getMobile()+"\n\n"+
                            "Avail. Balance : "+personal.getBalance()+"\n");
              cur_bal = personal.getBalance();

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,ps_arraylist);
        listView1.setAdapter(arrayAdapter);

        button=findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("CURR_BAL",cur_bal);
                startActivity(intent);
            }
        });

        toolbar_3  = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar_3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.menu_home){
            Toast.makeText(MainActivity.this,"Home ",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.menu_history){
                Intent intent = new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
        }
        else if (id==R.id.menu_soon){
        Toast.makeText(MainActivity.this,"Coming Soon!!!",Toast.LENGTH_SHORT).show();

        }
        else if (id==R.id.menu_exit){
            new AlertDialog.Builder(MainActivity.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                    Toast.makeText(MainActivity.this,"Tap yes to exit from this app",Toast.LENGTH_SHORT).show();
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