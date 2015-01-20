package com.example.jason.mywardrobe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import model.Clothes;


public class MatchActivity extends ActionBarActivity {

    private  List<Clothes> clothesList;
    private  List<Clothes> topList;
    private  List<Clothes> belowList;
    private  int numTop = 0;
    private  int numBelow = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        clothesList = new ArrayList<Clothes>();

        for(Clothes clothes : clothesList){
            if(clothes.getKind().equals("下半身"))belowList.add(clothes);
            else topList.add(clothes);
        }

        ImageButton btn_topImage = (ImageButton) findViewById(R.id.imgbtn_clothes);
        Bitmap myImg = BitmapFactory.decodeFile(topList.get(0).getPath());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);
        btn_topImage.setImageBitmap(rotated);

        ImageButton btn_belowImage = (ImageButton) findViewById(R.id.imgbtn_pants);
        Bitmap myImg2 = BitmapFactory.decodeFile(topList.get(0).getPath());
        Matrix matrix2 = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated2 = Bitmap.createBitmap(myImg2, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix2, true);
        btn_belowImage.setImageBitmap(rotated2);

        ImageButton btn_topLeft = (ImageButton) findViewById(R.id.imgbtn_clthleft);
        btn_topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numTop>0){
                    numTop--;
                    Bitmap myImg = BitmapFactory.decodeFile(topList.get(numTop).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)v;
                    imageButton.setImageBitmap(rotated);
                }
            }
        });

        ImageButton btn_belowLeft = (ImageButton) findViewById(R.id.imgbtn_pntsleft);
        btn_belowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numBelow>0){
                    numBelow--;
                    Bitmap myImg = BitmapFactory.decodeFile(belowList.get(numBelow).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)v;
                    imageButton.setImageBitmap(rotated);
                }
            }
        });

        ImageButton btn_topRight = (ImageButton) findViewById(R.id.imgbtn_clthright);
        btn_topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numTop<topList.size()-1){
                    numTop++;
                    Bitmap myImg = BitmapFactory.decodeFile(topList.get(numTop).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)v;
                    imageButton.setImageBitmap(rotated);
                }
            }
        });

        ImageButton btn_belowRight = (ImageButton) findViewById(R.id.imgbtn_pntsright);
        btn_belowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numBelow<belowList.size()-1){
                    numBelow++;
                    Bitmap myImg = BitmapFactory.decodeFile(belowList.get(numBelow).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)v;
                    imageButton.setImageBitmap(rotated);
                }
            }
        });

        Button btn_clearTop = (Button) findViewById(R.id.btn_clearTop);
        btn_clearTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numTop==topList.size()-1){
                    numTop--;
                    Bitmap myImg = BitmapFactory.decodeFile(topList.get(numTop).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)v;
                    imageButton.setImageBitmap(rotated);
                }
            }
        });

        Button btn_clearBelow = (Button) findViewById(R.id.btn_clearBelow);
        Button btn_clearallTop = (Button) findViewById(R.id.btn_clearallTop);
        Button btn_clearallBelow = (Button) findViewById(R.id.btn_clearallBelow);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match, menu);
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
