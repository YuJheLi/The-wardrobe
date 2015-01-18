package com.example.jason.mywardrobe;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.BrowseAdapter;
import manager.ClothesManager;
import model.Clothes;
import manager.ClothesManager;
//import com.example.jason.mywardrobe.R;

public class BrowseActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener{


    private OnFragmentInteractionListener mFragmentInteractionListener;
    private SwipeRefreshLayout swipeRefreshLayout ;
    private ListView listView;
    private ArrayList<Clothes> clothesList;
    private GridView clothesListView;
    private BrowseAdapter browseAdapter;
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
        browseAdapter = new BrowseAdapter(this, clothesList);
        clothesListView = (GridView) findViewById(R.id.list_question);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        clothesListView.setAdapter(browseAdapter);
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

    }
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }
}
