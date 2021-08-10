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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deutschebank.database.User_Database;
import com.example.deutschebank.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
ListView listView2;
Toolbar toolbar_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView2=findViewById(R.id.user_list_view);



        User_Database user_database = new User_Database(MainActivity2.this);
        User user1 = new User();
        user1.setClient_id(1);
        user1.setHolder_name("USER1");
        user1.setAccount_number(101);
        user1.setIfsc_code("SBI0007866");

        user_database.add_user_detail(user1);

        User user2 = new User();
        user2.setClient_id(2);
        user2.setHolder_name("USER2");
        user2.setAccount_number(112);
        user2.setIfsc_code("SBI0007812");

        user_database.add_user_detail(user2);

        User user3 = new User();
        user3.setClient_id(3);
        user3.setHolder_name("USER3");
        user3.setAccount_number(123);
        user3.setIfsc_code("SBI0007823");

        user_database.add_user_detail(user3);

        User user4 = new User();
        user4.setClient_id(4);
        user4.setHolder_name("USER4");
        user4.setAccount_number(141);
        user4.setIfsc_code("SBI0007841");

        user_database.add_user_detail(user4);

        User user5 = new User();
        user5.setClient_id(5);
        user5.setHolder_name("USER5");
        user5.setAccount_number(155);
        user5.setIfsc_code("SBI0007155");

        user_database.add_user_detail(user5);

        User user6 = new User();
        user6.setClient_id(6);
        user6.setHolder_name("USER6");
        user6.setAccount_number(167);
        user6.setIfsc_code("SBI0007166");

        user_database.add_user_detail(user6);

        User user7 = new User();
        user7.setClient_id(7);
        user7.setHolder_name("USER7");
        user7.setAccount_number(789);
        user7.setIfsc_code("SBI0007866");

        user_database.add_user_detail(user7);

        User user8 = new User();
        user8.setClient_id(8);
        user8.setHolder_name("USER8");
        user8.setAccount_number(915);
        user8.setIfsc_code("SBI0007866");

        user_database.add_user_detail(user8);

        User user9 = new User();
        user9.setClient_id(9);
        user9.setHolder_name("USER9");
        user9.setAccount_number(1471);
        user9.setIfsc_code("SBI0007866");

        user_database.add_user_detail(user9);

        User user10 = new User();
        user10.setClient_id(10);
        user10.setHolder_name("USER10");
        user10.setAccount_number(2569);
        user10.setIfsc_code("SBI0007256");

        user_database.add_user_detail(user10);



        ArrayList<String> user_arraylist = new ArrayList<>();

        List<User> user_list = user_database.get_user_list();

        for (User uc:user_list){
            Log.d("dbAMAN","Holder Name : "+uc.getHolder_name()+"\n"+
                    "Account number : " +uc.getAccount_number()+"\n"+
                    "IFSC CODE : "+uc.getIfsc_code()+"\n");

            user_arraylist.add("\n"+"Client ID : "+uc.getClient_id()+"\n\n"+
                            "Holder Name : "+uc.getHolder_name()+ "\n\n"+
                            "Account Number : "+uc.getAccount_number()+"\n\n"+
                            "IFSC CODE : "+uc.getIfsc_code()+"\n");

        }

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,user_arraylist);
        listView2.setAdapter(arrayAdapter1);


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity2.this,"User "+(position+1)+" is selected",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                int x = position+1;

                intent.putExtra("Position",x);
                startActivity(intent);
            }
        });

        toolbar_2 = findViewById(R.id.toolbar2);
        toolbar_2.setTitle("Beneficiary List");
        setSupportActionBar(toolbar_2);
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
            Toast.makeText(MainActivity2.this,"Home ",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.menu_history){
            Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
            startActivity(intent);
        }
        else if (id==R.id.menu_soon){
            Toast.makeText(MainActivity2.this,"Coming Soon!!!",Toast.LENGTH_SHORT).show();

        }
        else if (id==R.id.menu_exit){
            new AlertDialog.Builder(MainActivity2.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                    Toast.makeText(MainActivity2.this,"Tap yes to exit from this app",Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
        return  true;
    }
}
