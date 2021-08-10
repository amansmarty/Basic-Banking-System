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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deutschebank.database.Personal_Database;
import com.example.deutschebank.database.User_Database;
import com.example.deutschebank.model.History;
import com.example.deutschebank.model.Personal;
import com.example.deutschebank.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    int pos;
    int bal1;
    ListView listView3;
    EditText editText;
    Button button;
    int Avail_bal;
    Toolbar toolbar_3;
    User_Database db;
    public ArrayList<String> bf_historylist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editText = findViewById(R.id.editTextPhone3);

        Personal_Database ps = new Personal_Database(MainActivity3.this);
        Personal ps1 = new Personal();


        Intent intent = getIntent();

        pos =intent.getIntExtra("Position",2);
        Avail_bal = intent.getIntExtra("CURR_BAL",45000);


        listView3=findViewById(R.id.bc_List_lv);

        db=new User_Database(MainActivity3.this);
        ArrayList<String> bf_arraylist = new ArrayList<>();

        List<User> bf_list = db.get_bc_list(pos);

        for (User uc:bf_list){

            bf_arraylist.add("\n"+"Client ID : "+uc.getClient_id()+"\n\n"+
                    "Holder Name : "+uc.getHolder_name()+ "\n\n"+
                    "Account Number : "+uc.getAccount_number()+"\n\n"+
                    "IFSC CODE : "+uc.getIfsc_code()+"\n");

        }

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(MainActivity3.this, android.R.layout.simple_list_item_1,bf_arraylist);
        listView3.setAdapter(arrayAdapter2);

        button=findViewById(R.id.btn_proceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bal = editText.getText().toString();
                bal1 = Integer.parseInt(bal);
                ps1.setClient_id(101);

                if(bal1==0){
                    Toast.makeText(MainActivity3.this,"Please enter your amount",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Avail_bal>bal1) {

                    int rest = Avail_bal - bal1;
                    ps1.setBalance(rest);
                    ps.updateContact(ps1);

                    new AlertDialog.Builder(MainActivity3.this).setIcon(R.drawable.ic_baseline_warning_24)
                            .setTitle("Warning")
                            .setMessage("Tap yes if you want to pay")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    new AlertDialog.Builder(MainActivity3.this).setIcon(R.drawable.check)
                                            .setTitle("SUCCESSFULL")
                                            .setMessage("Payment of $"+bal1+" successful.")
                                            .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Toast.makeText(MainActivity3.this, "  PAYMENT SUCCESSFULL!!! \n \n AVAILABLE BALANCE IS $" + rest, Toast.LENGTH_LONG).show();
                                                             add();
                                                             finish();
                                                }
                                            }).show();
                                }
                            }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity3.this,"Tap no if you want to cancel the payment",Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();




                }
                else {
                    Toast.makeText(MainActivity3.this,"Insufficient Balance !!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
        toolbar_3=findViewById(R.id.toolbar4);
        toolbar_3.setTitle("Payment");
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
            Toast.makeText(MainActivity3.this,"Home ",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity3.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.menu_history){
            Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
            startActivity(intent);
        }
        else if (id==R.id.menu_soon){
            Toast.makeText(MainActivity3.this,"Coming Soon!!!",Toast.LENGTH_SHORT).show();

        }
        else if (id==R.id.menu_exit){

            new AlertDialog.Builder(MainActivity3.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                    Toast.makeText(MainActivity3.this,"Tap yes to exit from this app",Toast.LENGTH_SHORT).show();
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

    public void add(){
        History history = new History();
        List<User> history_list = db.get_list(pos);
        for (User uc1:history_list){
            history.setClient_id(uc1.getClient_id());
            history.setHolder_name(uc1.getHolder_name());
            history.setAccount_number(uc1.getAccount_number());
            history.setBalance(bal1);

        }
        db.add_list(history);
    }
}