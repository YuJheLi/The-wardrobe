package com.example.jason.mywardrobe;

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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import model.Clothes;
import model.DataWrapper;


@SuppressWarnings("ALL")
public class MatchActivity extends ActionBarActivity {

    private  ArrayList<Clothes> clothesList;
    private  ArrayList<Clothes> topList = new ArrayList<Clothes>();
    private  ArrayList<Clothes> belowList = new ArrayList<Clothes>();
    private  int numTop = 0;
    private  int numBelow = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("here", "-1");
        super.onCreate(savedInstanceState);
        Log.d("here", "0");
        setContentView(R.layout.activity_match);
        getSupportActionBar().hide();
        Log.d("here", "0.5");
        DataWrapper dw=(DataWrapper)getIntent().getSerializableExtra("chooselist");
        Log.d("here", "1");
        clothesList = dw.getParliaments();
        Log.d("here","1.5");
        for(Clothes clothes : clothesList){
            if(clothes.getKind().equals(getResources().getStringArray(R.array.kind)[1]))belowList.add(clothes);
            else topList.add(clothes);
        }
        Log.d("here", "2");
        ImageButton btn_topImage = (ImageButton) findViewById(R.id.imgbtn_clothes);
        Bitmap myImg = BitmapFactory.decodeFile(topList.get(0).getPath());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);
        btn_topImage.setImageBitmap(rotated);

        ImageButton btn_belowImage = (ImageButton) findViewById(R.id.imgbtn_pants);
        Bitmap myImg2 = BitmapFactory.decodeFile(belowList.get(0).getPath());
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
                    ImageButton imageButton = (ImageButton) findViewById(R.id.imgbtn_clothes);
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
                    imageButton.setImageDrawable(la);


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
                    ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_pants);
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
                    imageButton.setImageDrawable(la);

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
                    ImageButton imageButton = (ImageButton) findViewById(R.id.imgbtn_clothes);
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
                    imageButton.setImageDrawable(la);
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
                    ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_pants);
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
                    imageButton.setImageDrawable(la);
                }
            }
        });

        Button btn_clearTop = (Button) findViewById(R.id.btn_clearTop);
        btn_clearTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topList.size()==1){
                    ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_clothes);
                    //imageButton.setBackground(getResources().getDrawable(R.drawable.no_clothes));
                    imageButton.setImageResource(R.drawable.no_clothes);
                }
                else if(numTop==topList.size()-1 && topList.size()>1){
                    topList.remove(numTop);
                    numTop--;
                    Bitmap myImg = BitmapFactory.decodeFile(topList.get(numTop).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton) findViewById(R.id.imgbtn_clothes);
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
                    imageButton.setImageDrawable(la);
                }
                else {
                    topList.remove(numTop);
                    Bitmap myImg = BitmapFactory.decodeFile(topList.get(numTop).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton) findViewById(R.id.imgbtn_clothes);
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
                    imageButton.setImageDrawable(la);
                }
            }
        });

        Button btn_clearBelow = (Button) findViewById(R.id.btn_clearBelow);
        btn_clearBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(belowList.size()==1){
                    ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_pants);
                    //imageButton.setBackground(getResources().getDrawable(R.drawable.no_clothes));
                    imageButton.setImageResource(R.drawable.no_clothes);
                    Log.d("sleepy",Integer.toString(numBelow));
                }
                else if(numBelow==belowList.size()-1 && belowList.size()>1){
                    Log.d("sleepy2",Integer.toString(numBelow));
                    belowList.remove(numBelow);
                    numBelow--;
                    Bitmap myImg = BitmapFactory.decodeFile(belowList.get(numBelow).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_pants);
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
                    imageButton.setImageDrawable(la);
                }
                else {
                    Log.d("sleepy",Integer.toString(numBelow));
                    belowList.remove(numBelow);
                    Bitmap myImg = BitmapFactory.decodeFile(belowList.get(numBelow).getPath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(0);
                    Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                            matrix, true);
                    ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_pants);
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
                    imageButton.setImageDrawable(la);
                }

            }
        });
        Button btn_clearallTop = (Button) findViewById(R.id.btn_clearallTop);
        btn_clearallTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topList.clear();
                ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_clothes);
                //imageButton.setBackground(getResources().getDrawable(R.drawable.no_clothes));
                imageButton.setImageResource(R.drawable.no_clothes);
            }
        });
        Button btn_clearallBelow = (Button) findViewById(R.id.btn_clearallBelow);
        btn_clearallBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belowList.clear();
                ImageButton imageButton = (ImageButton)findViewById(R.id.imgbtn_pants);
                //imageButton.setBackground(getResources().getDrawable(R.drawable.no_clothes));
                imageButton.setImageResource(R.drawable.no_clothes);
            }
        });

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

    @Override
    public void onBackPressed() {


        Intent in=new Intent();
        DataWrapper data=new DataWrapper(clothesList);
        in.putExtra("matchlist", data);

        setResult(RESULT_OK,in);
        finish();

    }

}
