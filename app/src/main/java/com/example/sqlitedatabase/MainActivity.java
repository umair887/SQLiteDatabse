package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addbtn;
    ListView list;
    TextView name;
    TextView rolno;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> myArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbtn = findViewById(R.id.add);
        list = findViewById(R.id.listView);
        name = findViewById(R.id.name);
        rolno = findViewById(R.id.rollNumber);
        myArray = new ArrayList<String>();

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        myArray = db.GetStudentsData();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArray);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myArray.remove(position);
                list.setAdapter(arrayAdapter);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myArray.add("Student Name is " + name.getText().toString() + " and Roll Number is " + rolno.getText().toString());
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                db.AddStudent(name.getText().toString(),rolno.getText().toString());
                list.setAdapter(arrayAdapter);
                name.setText("");
                rolno.setText("");
            }
        });
    }
}