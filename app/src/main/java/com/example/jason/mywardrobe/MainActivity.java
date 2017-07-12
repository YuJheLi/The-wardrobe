package com.example.jason.mywardrobe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import model.Matches;

//import com.example.jason.mywardrobe.R;


public class MainActivity extends ActionBarActivity {
    private ImageButton mybox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);getSupportActionBar().hide();

        ImageButton btn = (ImageButton) findViewById(R.id.button);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddclothActivity.class);
                startActivity(intent);
            }
        });
        ImageButton imgbtn = (ImageButton)findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),BrowseActivity.class);
                startActivity(intent);
            }
        });
        mybox=(ImageButton)findViewById(R.id.mybox);
        mybox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CollectActivity.class);
                startActivity(intent);
            }
        });
        ImageButton randbtn = (ImageButton) findViewById(R.id.button7);
        randbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RandomActivity.class);
                startActivity(intent);
            }
        });
        ImageButton Qbutton = (ImageButton) findViewById(R.id.imageQ);
        Qbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert();
            }
        });








    }
    public void alert( ) {


        AlertDialog.Builder alertadd = new AlertDialog.Builder(
                MainActivity.this);
        alertadd.setTitle("App說明");

        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        final View view = factory.inflate(R.layout.introduction, null);

        alertadd.setView(view);

        alertadd.setNegativeButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {
            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {


            }
        });
        alertadd.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */

}
