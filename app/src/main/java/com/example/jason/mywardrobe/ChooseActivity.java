package com.example.jason.mywardrobe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import adapter.BrowseAdapter;
import adapter.ChooseAdapter;
import manager.ClothesManager;
import model.Clothes;
import model.DataWrapper;


public class ChooseActivity extends ActionBarActivity {
    private ChooseAdapter ccadpter;
    private static final String TAG = "ChooseActivity";


    private ArrayList<Clothes> resultClothes;
    private ArrayList<Clothes> clothesList;
    private GridView clothesListView;
    private boolean[] choosenumber;

    private Button chooseall;
    private Button choosecancell;
    private Button addtomatch;
    private Button deletechoose;
    private TextView number;
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

        DataWrapper dw=(DataWrapper)getIntent().getSerializableExtra("list");

        Log.d(TAG, "search success");


        clothesList = dw.getParliaments();




        clothesListView=(GridView) findViewById(R.id.choose_grid);
        ccadpter=new ChooseAdapter( clothesList , this);

        clothesListView.setAdapter(ccadpter);
        clothesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ccadpter.chiceState(position);
                number.setText(formatString(ccadpter.number()));
            }
        });
        chooseall=(Button)findViewById(R.id.btn_all);
        chooseall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < clothesList.size(); i++) {
                    ccadpter.alltrue(i);
                }
                number.setText(formatString(ccadpter.number()));
            }
        });
        choosecancell=(Button)findViewById(R.id.btn_cancel);

        choosecancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < clothesList.size(); i++) {
                    ccadpter.allfalse(i);
                }
                number.setText(formatString(ccadpter.number()));
            }
        });
        addtomatch=(Button)findViewById(R.id.btn_addtomatch);
        addtomatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultClothes=new ArrayList<Clothes>();
                choosenumber=ccadpter.getchoose();
                for (int i = 0; i < clothesList.size(); i++) {
                   if(choosenumber[i]==true){
                       resultClothes.add(clothesList.get(i));
                   }
                }




                if(!resultClothes.isEmpty()){
                    Intent in=new Intent();
                    DataWrapper data=new DataWrapper(resultClothes);
                    in.putExtra("chooselist", data);

                    setResult(RESULT_OK,in);
                    finish();
                }
            }
        });
        deletechoose=(Button)findViewById(R.id.deletebtn);
        deletechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ccadpter.number()!=0) {
                    deletetfiles();
                }
            }
        });




     number=(TextView)findViewById(R.id.numbergg);
        number.setText(formatString(ccadpter.number()));
    }
    public void deletetfiles(){

        AlertDialog.Builder ad=new AlertDialog.Builder(ChooseActivity.this);

        ad.setTitle(R.string.dialog_delete);

        ad.setMessage(R.string.dialog_deletesome);

        ad.setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int i) {
                resultClothes=new ArrayList<Clothes>();
                choosenumber=ccadpter.getchoose();
                for (int j = 0; j < clothesList.size(); j++) {
                    if(choosenumber[j]==true){
                        resultClothes.add(clothesList.get(j));
                    }
                }
                if(!resultClothes.isEmpty()){
                    Intent in=new Intent();
                    DataWrapper data=new DataWrapper(resultClothes);
                    in.putExtra("deletelist", data);

                    setResult(5566,in);
                    finish();
                }



            }

        });

        ad.setNegativeButton(R.string.dialog_no,new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int i) {

            }

        });

        ad.show();//顯示訊息視窗

    }




    private String formatString(int count) {
        return String.format(getString(R.string.selection), count);
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
