package com.example.sean.vancouvertop10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private String[] list;
    private String appendedString = "";
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        load();
        populateListView();
    }

    private void load() {
    }

    private void populateListView() {
        //create list of items
        list = new String[] {"a", "b", "c", "", "", "", "", "", "", ""};

        //build adaptor
        adapter = new ArrayAdapter<String>(
                this,                   //context for the activity
                R.layout.items,         //layout to use (created xml file called items.xml)
                list);                  //items to be displayed

        //configure the list view
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void onClickSave(View v) {
        Toast.makeText(this, "save completed", Toast.LENGTH_LONG).show();
        convertItemList();
        save();
    }

    private void convertItemList() {
        for (int i = 0; i < list.length; i++) {
            String item = listView.getAdapter().getItem(i).toString();
            appendedString = appendedString + item;
        }
    }

    private void save() {
        try {
            FileOutputStream fos = openFileOutput("itemsList.txt", Context.MODE_PRIVATE);
            fos.write(appendedString.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
