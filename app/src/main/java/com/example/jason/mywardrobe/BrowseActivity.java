package com.example.jason.mywardrobe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.BrowseAdapter;
import manager.ClothesManager;
import model.Clothes;
import model.DataWrapper;
//import com.example.jason.mywardrobe.R;

public class BrowseActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {
    private static final String TAG = "BrowseActivity";


    private OnFragmentInteractionListener mFragmentInteractionListener;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ArrayList<Clothes> resultClothes;
    private ArrayList<Clothes> searchClothes;
    private ArrayList<Clothes> clothesList;
    private ArrayList<Clothes> chooseClothes;

    private GridView clothesListView;
    private BrowseAdapter browseAdapter;
    private Button multichoose;
    private Button choose;
    private Button search;
    private boolean multi = false;
    private static final int CHOOSE_OVER = 1;
    private static final int SEARCH_OVER = 2447;
    private static final int MATCH_OVER= 3;
    // private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        ClothesManager clothesManager = null;
        try {
            clothesManager = new ClothesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clothesList = clothesManager.getWardrobe();
        resultClothes=clothesManager.getWardrobe();
        searchClothes=clothesManager.getWardrobe();

        browseAdapter = new BrowseAdapter(this, clothesList);
        clothesListView = (GridView) findViewById(R.id.list_question);
        //LayoutInflater layoutInflater = LayoutInflater.from(this);
        clothesListView.setAdapter(browseAdapter);
        clothesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BrowseActivity.this, "你選擇了" + (position + 1) + " 號圖片", Toast.LENGTH_SHORT).show();


            }
        });

       choose=(Button)findViewById(R.id.btn_multi);
       choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrowseActivity.this, ChooseActivity.class);

                DataWrapper data=new DataWrapper(searchClothes);
                intent.putExtra("list", data);

                startActivityForResult(intent, CHOOSE_OVER);
            }
        });
       search=(Button)findViewById(R.id.btn_search);
       search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search=new Intent(BrowseActivity.this,SearchActivity.class);
                startActivityForResult(intent_search, SEARCH_OVER);

            }
        });


        //clothesListView.setEmptyView(view.findViewById(R.id.empty));
        // clothesListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
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



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setBackgroundColor(Color.GREEN);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (responseCode == RESULT_OK) {

            if (requestCode == CHOOSE_OVER) {

                //String path = getPathFromCamera(data);

                 // Intent intent = new Intent(this, ChooseClothesActivity.class);
                //intent.putExtra("clothes", resultClothes);
                //startActivityForResult(intent, CREATE_NEW_QUESTION);
                Log.d(TAG,"chooseover");
                DataWrapper dw=(DataWrapper)data.getSerializableExtra("chooselist");

                Log.d(TAG,"choose success");


               chooseClothes = dw.getParliaments();

                Intent in=new Intent(BrowseActivity.this,MatchActivity.class);
                DataWrapper datatoMatch=new DataWrapper(chooseClothes);
                in.putExtra("chooselist", datatoMatch);
                Log.d(TAG,"entering match");
                startActivity(in);




            }else if (requestCode == SEARCH_OVER) {
                Log.d(TAG,"inin");
                DataWrapper dw=(DataWrapper)data.getSerializableExtra("list");

                Log.d(TAG,"search success");


                searchClothes = dw.getParliaments();
                browseAdapter.dataset(searchClothes);
                //browseAdapter = new BrowseAdapter(this,  searchClothes);

                //LayoutInflater layoutInflater = LayoutInflater.from(this);
               // clothesListView.setAdapter(browseAdapter);




            }else if (requestCode == MATCH_OVER) {


            }

        }else if(responseCode == RESULT_CANCELED){



        }


    }
}
