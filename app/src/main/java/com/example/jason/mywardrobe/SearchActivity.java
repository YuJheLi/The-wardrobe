package com.example.jason.mywardrobe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import manager.ClothesManager;
import model.Clothes;
import model.DataWrapper;


public class SearchActivity extends ActionBarActivity {
    private ArrayList<Clothes> clothesList;
    private ArrayList<Clothes> clothesbox;
    private Spinner color;
    private Spinner kind;
    private Spinner texture;
    private Spinner use;
    private Spinner type;
    private EditText name;
    private Button send;
    private ArrayAdapter<String> uptypeadapter;
    private ArrayAdapter<String> downtypeadapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ClothesManager clothesManager = null;
        try {
            clothesManager = new ClothesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clothesList = clothesManager.getWardrobe();


        kind=(Spinner)findViewById(R.id.search_kind);
        name=(EditText)findViewById(R.id.search_editText);
        String[] kinds = getResources().getStringArray(R.array.kind);
        ArrayAdapter<String> kindadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, kinds);
        kind.setAdapter(kindadapter);
        color=(Spinner)findViewById(R.id.search_color);
        String[] colors = getResources().getStringArray(R.array.colors2);
        ArrayAdapter<String> coloradapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colors);
        color.setAdapter(coloradapter);

        type=(Spinner)findViewById(R.id.search_type);
        String[] uptypes = getResources().getStringArray(R.array.uptype2);
        uptypeadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, uptypes);
        String[] downtypes = getResources().getStringArray(R.array.downtype2);
        downtypeadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, downtypes);
        kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index =parent.getSelectedItemPosition();
                if(index==0){
                    type.setAdapter(uptypeadapter);
                }else if(index==1){
                    type.setAdapter(downtypeadapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        use=(Spinner)findViewById(R.id.search_use);
        String[] uses = getResources().getStringArray(R.array.use2);
        ArrayAdapter<String>useadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,uses);
        use.setAdapter(useadapter);


        texture=(Spinner)findViewById(R.id.search_texture);
        String[] textures = getResources().getStringArray(R.array.texture2);
        ArrayAdapter<String>textureadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,textures);
        texture.setAdapter(textureadapter);

        send=(Button)findViewById(R.id.searchfor);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namess = name.getText().toString();
                String colorss = getResources().getStringArray(R.array.colors2)[color.getSelectedItemPosition()];
                String kindss = getResources().getStringArray(R.array.kind)[kind.getSelectedItemPosition()];
                String usess = getResources().getStringArray(R.array.use2)[use.getSelectedItemPosition()];
                String texturess = getResources().getStringArray(R.array.texture2)[texture.getSelectedItemPosition()];
                String typess;
                if (index == 0) {
                    typess = getResources().getStringArray(R.array.uptype2)[type.getSelectedItemPosition()];
                } else if(index==1){
                    typess = getResources().getStringArray(R.array.downtype2)[type.getSelectedItemPosition()];
                }else{
                    typess="";
                }
                try {
                    ClothesManager manager = new ClothesManager();

                    Clothes cloth = new Clothes( "", namess, kindss, typess, colorss, usess, texturess);

                    clothesbox=manager.search(cloth);




                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent in=new Intent();
                DataWrapper data=new DataWrapper(clothesbox);
                in.putExtra("list", data);

                setResult(RESULT_OK,in);
                finish();










            }
        });







    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
