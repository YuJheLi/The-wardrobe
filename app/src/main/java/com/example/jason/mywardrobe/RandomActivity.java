package com.example.jason.mywardrobe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import manager.ClothesManager;
import manager.MatchesManager;
import model.Clothes;
import model.DataWrapper;
import model.Matches;

@SuppressWarnings("ALL")
public class RandomActivity extends ActionBarActivity {
    private ClothesManager clothesManager;
    private ArrayList<Clothes> clothesArrayList;
    private ArrayList<Clothes> belowList;
    private ArrayList<Clothes> topList;
    private MatchesManager matchbox;
    private int randtop;
    private int randbelow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        getSupportActionBar().hide();
        try {
            clothesManager = new ClothesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clothesArrayList = clothesManager.getWardrobe();
        belowList = new ArrayList<Clothes>();
        topList = new ArrayList<Clothes>();
        for(Clothes clothes : clothesArrayList){
            if(clothes.getKind().equals(getResources().getStringArray(R.array.kind)[1]))
                belowList.add(clothes);
            else topList.add(clothes);
        }
        ImageView top = (ImageView) findViewById(R.id.randimgtop);
        top.setImageResource(R.drawable.cart_empty);
        ImageView below = (ImageView) findViewById(R.id.randimgbelow);
        below.setImageResource(R.drawable.cart_empty);
        goRandom();
        Button randombtn = (Button) findViewById(R.id.btn_randmatch);
        randombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRandom();
            }
        });
        Button saverand = (Button) findViewById(R.id.btn_saverand);
        saverand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    matchbox=new MatchesManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(topList.size()!=0&&belowList.size()!=0){
                    Matches newmatch=new Matches(topList.get(randtop).getPath(),belowList.get(randbelow).getPath());
                    if(matchbox.isExist(newmatch)){
                        RepeatSave();


                    }else{

                        try {
                            matchbox.addMatch(newmatch);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ConfirmSave();


                    }






                }

            }
        });
    }
    public void RepeatSave(){

        AlertDialog.Builder ad=new AlertDialog.Builder(RandomActivity.this);
        ad.setTitle(R.string.dialog_repeat);
        ad.setMessage(R.string.dialog_repeat_exist);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }

        });

        ad.show();

    }
    public void ConfirmSave(){

        AlertDialog.Builder ad=new AlertDialog.Builder(RandomActivity.this);
        ad.setTitle(R.string.dialog_savematch);
        ad.setMessage(R.string.dialog_savematch_sucess);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {


            }

        });

        /*
        ad.setNegativeButton(R.string.dialog_no,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
            }
        });*/

        ad.show();

    }


    private void goRandom(){
        if(!topList.isEmpty() && !belowList.isEmpty()){
            ImageView top = (ImageView) findViewById(R.id.randimgtop);
            Random random = new Random();
            randtop = random.nextInt(topList.size());
            Bitmap myImg = BitmapFactory.decodeFile(topList.get(randtop).getPath());
            Matrix matrix = new Matrix();
            matrix.postRotate(0);
            Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                    matrix, true);
            //imageButton.setImageBitmap(rotated);

            Drawable[] array = new Drawable[2];

            ShapeDrawable border = new ShapeDrawable();
            border.getPaint().setColor(Color.LTGRAY);
            array[0]=border;
            array[1] = new BitmapDrawable(rotated);
            LayerDrawable la=null;
            la= new LayerDrawable(array);

            la.setLayerInset(0, 0, 0, 0, 0);
            la.setLayerInset(1, 20, 20, 20, 20);
            top.setImageDrawable(la);

            ImageView below = (ImageView) findViewById(R.id.randimgbelow);
            randbelow = random.nextInt(belowList.size());
            Bitmap myImg2 = BitmapFactory.decodeFile(belowList.get(randbelow).getPath());
            Matrix matrix2 = new Matrix();
            matrix2.postRotate(0);
            Bitmap rotated2 = Bitmap.createBitmap(myImg2, 0, 0, myImg2.getWidth(), myImg2.getHeight(),
                    matrix2, true);
            //imageButton.setImageBitmap(rotated);

            Drawable[] array2 = new Drawable[2];

            ShapeDrawable border2 = new ShapeDrawable();
            border2.getPaint().setColor(Color.LTGRAY);
            array2[0]=border2;
            array2[1] = new BitmapDrawable(rotated2);
            LayerDrawable la2=null;
            la2= new LayerDrawable(array2);

            la2.setLayerInset(0, 0, 0, 0, 0);
            la2.setLayerInset(1, 20, 20, 20, 20);
            below.setImageDrawable(la2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random, menu);
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
