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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import adapter.ChooseAdapter;
import adapter.CollectAdapter;
import manager.ClothesManager;
import manager.MatchesManager;
import model.Clothes;
import model.Matches;


@SuppressWarnings("ALL")
public class CollectActivity extends ActionBarActivity {

    private CollectAdapter coadpter;
    private static final String TAG = "CollectActivity";



    private ArrayList<Matches> matchesList;
    private GridView matchesListView;
    private TextView number;
    private Matches delete;



    private   MatchesManager matchesManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);


        try {
            matchesManager=new MatchesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        matchesList=matchesManager.getMatchbox();
        int n=matchesList.size();



        matchesListView=(GridView) findViewById(R.id.collect_gridview);
        coadpter=new CollectAdapter( matchesList , this);

        matchesListView.setAdapter(coadpter);
        matchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alert(matchesList.get(position));

            }
        });
        number=(TextView)findViewById(R.id.boxnumber);
        number.setText(formatString(n));

    }
    private String formatString(int count) {
        return String.format(getString(R.string.boxselection), count);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collect, menu);
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






    public void alert( Matches match_infor) {


        AlertDialog.Builder alertadd = new AlertDialog.Builder(
                CollectActivity.this);
        alertadd.setTitle(R.string.dialog_infor);

        LayoutInflater factory = LayoutInflater.from(CollectActivity.this);
        final View view = factory.inflate(R.layout.box_match_infor, null);

        ImageView dioimage = (ImageView) view.findViewById(R.id.dialog_image);


        ImageView photoImg = (ImageView) view.findViewById(R.id.match_infor_cloth);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.t_shirt_yellow);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(),
                R.drawable.pants);

        Bitmap myImg = BitmapFactory.decodeFile(match_infor.getClothes_path());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        Drawable[] array = new Drawable[3];

        ShapeDrawable back = new ShapeDrawable();
        back.getPaint().setColor(Color.DKGRAY);
        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(Color.GREEN);
        array[0]=back;
        array[1]=border;
        array[2] = new BitmapDrawable(rotated);
        //array[3] = new BitmapDrawable(bitmap3);
        LayerDrawable la=null;
        la= new LayerDrawable(array);
        la.setLayerInset(0, 0, 0, 0, 0);

        la.setLayerInset(1, 15, 10, 30, 0);
        la.setLayerInset(2, 20, 30, 40, 5);
       // la.setLayerInset(3, 0, 0, 350, 350);

        photoImg.setImageDrawable(la);

        ImageView photoImg2 = (ImageView) view.findViewById(R.id.match_infor_pants);

        Bitmap myImg2 = BitmapFactory.decodeFile(match_infor.getPants_path());
        Matrix matrix2 = new Matrix();
        matrix2.postRotate(0);
        Bitmap rotated2 = Bitmap.createBitmap(myImg2, 0, 0, myImg2.getWidth(), myImg2.getHeight(),
                matrix, true);

        Drawable[] array2 = new Drawable[3];



        ShapeDrawable border2 = new ShapeDrawable();
        border2.getPaint().setColor(Color.GREEN);
        array2[0]=back;
        array2[1]=border2;
        array2[2] = new BitmapDrawable(rotated2);
       // array2[3] = new BitmapDrawable(bitmap4);
        LayerDrawable la2=null;
        la2= new LayerDrawable(array2);

        la2.setLayerInset(0, 0, 0, 0, 0);
        la2.setLayerInset(1, 15, 0, 30, 10);
        la2.setLayerInset(2, 20, 5, 40, 30);
       //la2.setLayerInset(3,0, 0, 350, 350);
        photoImg2.setImageDrawable(la2);




        delete = match_infor;
        alertadd.setView(view);
        alertadd.setPositiveButton(R.string.dialog_match, new DialogInterface.OnClickListener() {
            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {
                deletedialod();
            }
        });
        alertadd.setNegativeButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {
            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {


            }
        });

        alertadd.show();

    }
    public void deletedialod() {
        AlertDialog.Builder ad = new AlertDialog.Builder(CollectActivity.this);
        ad.setTitle(R.string.dialog_match);
        ad.setMessage(R.string.dialog_deletematch);
        ad.setPositiveButton(R.string.dialog_no, new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int i) {


            }

        });

        ad.setNegativeButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int i) {
                MatchesManager matchesManager = null;
                try {
                    matchesManager = new MatchesManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    matchesManager.delete_match(delete);
                    matchesList=matchesManager.getMatchbox();


                    coadpter.dataset(matchesList);
                    number.setText(formatString(matchesList.size()));




                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        ad.show();
    }




}
