package com.example.jason.mywardrobe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;

import adapter.BrowseAdapter;
import adapter.ChooseAdapter;
import manager.ClothesManager;
import model.Clothes;


public class ChooseActivity extends ActionBarActivity {
    private ChooseAdapter ccadpter;


    private ArrayList<Clothes> resultClothes;
    private ArrayList<Clothes> clothesList;
    private GridView clothesListView;
    private BrowseAdapter browseAdapter;
    private Button chooseall;
    private Button choosecancell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ClothesManager clothesManager = null;
        try {
            clothesManager = new ClothesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clothesList = clothesManager.getWardrobe();

        clothesListView=(GridView) findViewById(R.id.choose_grid);
        ccadpter=new ChooseAdapter( clothesList , this);

        clothesListView.setAdapter(ccadpter);
        clothesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ccadpter.chiceState(position);
            }
        });
        chooseall=(Button)findViewById(R.id.btn_all);
        chooseall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < clothesList.size(); i++) {
                    ccadpter.alltrue(i);
                }
            }
        });
        choosecancell=(Button)findViewById(R.id.btn_cancel);

        choosecancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < clothesList.size(); i++) {
                    ccadpter.allfalse(i);
                }
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
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
