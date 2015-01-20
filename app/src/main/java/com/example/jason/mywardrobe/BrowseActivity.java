package com.example.jason.mywardrobe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import adapter.BrowseAdapter;
import manager.ClothesManager;
import model.Clothes;
//import com.example.jason.mywardrobe.R;

public class BrowseActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {


    private OnFragmentInteractionListener mFragmentInteractionListener;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ArrayList<Clothes> resultClothes;
    private ArrayList<Clothes> clothesList;
    private GridView clothesListView;
    private BrowseAdapter browseAdapter;
    private Button multichoose;
    private Button choose;
    private Button search;
    private boolean multi = false;
    private static final int CHOOSE_OVER = 1;
    private static final int SEARCH_OVER = 2;
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
        browseAdapter = new BrowseAdapter(this, clothesList);
        clothesListView = (GridView) findViewById(R.id.list_question);
        //LayoutInflater layoutInflater = LayoutInflater.from(this);
        clothesListView.setAdapter(browseAdapter);
        clothesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BrowseActivity.this, "你選擇了" + (position + 1) + " 號圖片", Toast.LENGTH_SHORT).show();

               // clothesListView.getChildAt(position).getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
            }
        });

       choose=(Button)findViewById(R.id.btn_multi);
       choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseActivity.class);
                startActivity(intent);
            }
        });
        search=(Button)findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search=new Intent(v.getContext(),SearchActivity.class);
                startActivityForResult(intent_search,SEARCH_OVER);

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

    public void onRefresh() {

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

                //} else if (requestCode == IMAGE_FROM_GALLERY) {


                //} else if (requestCode == CREATE_NEW_QUESTION) {



        }

        } else if (responseCode == SEARCH_OVER) {


        }else if (responseCode == MATCH_OVER) {


        }


    }
}
