package com.example.jason.mywardrobe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import manager.ClothesManager;
import model.Clothes;


public class AddclothActivity extends ActionBarActivity {

    private static final String TAG = "AddclothActivity";
    private ImageView myImage = null;

    private Uri imageUri; //图片路径
    private String filename; //图片名称

    private static final int PHOTO_PICKED_WITH_DATA = 3021;
    private static final int CAMERA_WITH_DATA = 3023;
    private static final int IMAGE_FROM_GALLERY = 3024;
    private Spinner color;
    private Spinner kind;
    private Spinner texture;
    private Spinner use;
    private Spinner type;
    private EditText name;
    private Button  send;
    private ArrayAdapter<String> uptypeadapter;
    private ArrayAdapter<String> downtypeadapter;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcloth);




        myImage = (ImageView)findViewById(R.id.myimage);
        kind=(Spinner)findViewById(R.id.spkind);
        name=(EditText)findViewById(R.id.clothename);
        String[] kinds = getResources().getStringArray(R.array.kind);
        ArrayAdapter<String> kindadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, kinds);
        kind.setAdapter(kindadapter);
        color=(Spinner)findViewById(R.id.spcolor);
        String[] colors = getResources().getStringArray(R.array.colors);
        ArrayAdapter<String> coloradapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colors);
        color.setAdapter(coloradapter);

        type=(Spinner)findViewById(R.id.sptype);
        String[] uptypes = getResources().getStringArray(R.array.uptype);
        uptypeadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, uptypes);
        String[] downtypes = getResources().getStringArray(R.array.downtype);
        downtypeadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, downtypes);
        kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index =parent.getSelectedItemPosition();
                if(index==0){
                    type.setAdapter(uptypeadapter);
                }else if(index==1){
                    type.setAdapter(downtypeadapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        use=(Spinner)findViewById(R.id.spuse);
        String[] uses = getResources().getStringArray(R.array.use);
        ArrayAdapter<String>useadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,uses);
        use.setAdapter(useadapter);


        texture=(Spinner)findViewById(R.id.sptexture);
        String[] textures = getResources().getStringArray(R.array.texture);
        ArrayAdapter<String>textureadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,textures);
        texture.setAdapter(textureadapter);




        send=(Button)findViewById(R.id.addbtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(imageUri!=null) {
                    String namess = name.getText().toString();
                    String colorss = getResources().getStringArray(R.array.colors)[color.getSelectedItemPosition()];
                    String kindss = getResources().getStringArray(R.array.kind)[kind.getSelectedItemPosition()];
                    String usess = getResources().getStringArray(R.array.use)[use.getSelectedItemPosition()];
                    String texturess = getResources().getStringArray(R.array.texture)[texture.getSelectedItemPosition()];
                    String typess;
                    if (index == 0) {
                        typess = getResources().getStringArray(R.array.uptype)[type.getSelectedItemPosition()];
                    } else {
                        typess = getResources().getStringArray(R.array.downtype)[type.getSelectedItemPosition()];
                    }

                    try {
                        ClothesManager manager = new ClothesManager();
                        Log.d(TAG, "manager");
                        Clothes cloth = new Clothes( imageUri.getPath(), namess, kindss, typess, colorss, usess, texturess);
                        Log.d(TAG, "addsuccess");
                        manager.addclothes(cloth);
                        finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }





            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addcloth, menu);
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
            showImageSourceDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showImageSourceDialog() {

        final String[] options = {getString(R.string.option_take_photo), getString(R.string.option_choose_photo)};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddclothActivity.this);
        builder.setTitle(getString(R.string.option_photo_title));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Intent intent;

                switch (item) {
                    case 0:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                        imageUri = Uri.fromFile(f);
                        startActivityForResult(intent, CAMERA_WITH_DATA);
                        break;
                    case 1:
                        intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        intent.setType("image/*");
                        startActivityForResult(intent, IMAGE_FROM_GALLERY);
                        break;
                }

            }
        });
        builder.show();
    }


    protected void doCropPhoto(Bitmap data){
        Intent intent = getCropImageIntent(data);
        File f = new File(android.os.Environment
                .getExternalStorageDirectory(), "temp3.jpg");
        imageUri=Uri.fromFile(f);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);

    }
    protected void doCropPhoto2(){
        Intent intent = getCropImageIntentFor();

        Toast.makeText(AddclothActivity.this, "cut", Toast.LENGTH_SHORT).show();

        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(imageUri);
        this.sendBroadcast(intentBc);

        startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);



    }



    public static Intent getCropImageIntent(Bitmap data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        intent.putExtra("data", data);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);


        return intent;
    }
    public  Intent getCropImageIntentFor() {
        Intent intent = new Intent("com.android.camera.action.CROP");

       intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("scale", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY",340);
        intent.putExtra("return-data", true);
        File dir=new File(Environment.getExternalStorageDirectory(),"MyWardrobe"+File.separator+"clothes");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(System.currentTimeMillis());
        filename = format.format(date);
        File f = new File(dir.getPath(), filename+".jpg");


        imageUri=Uri.fromFile(f);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (responseCode == RESULT_OK) {

            if (requestCode == PHOTO_PICKED_WITH_DATA) {

                Bitmap bmp = null;
                try {
                    //图片解析成Bitmap对象
                    bmp = BitmapFactory.decodeStream(
                            getContentResolver().openInputStream(imageUri));


                    //将剪裁后照片显示出来
                } catch(FileNotFoundException e) {
                    e.printStackTrace();
                }

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int screenWidth = size.x;
                double scaleFactor = (double) screenWidth / bmp.getWidth();
                Bitmap scaledBmp = Bitmap.createScaledBitmap(bmp, screenWidth,
                        (int) (scaleFactor * bmp.getHeight()), false);
                myImage.setImageBitmap(scaledBmp);
               //String path = getPathFromCamera(data);
                //Log.d(TAG, path);


               // Intent intent = new Intent(this, CreateActivity.class);
                //intent.putExtra("imagePath", path);
                //startActivityForResult(intent, CREATE_NEW_QUESTION);



            } else if (requestCode == IMAGE_FROM_GALLERY) {

              String path = getPathFromGallery(data);
                //Log.d(TAG, path);
                imageUri=data.getData();




                // Scale image
                doCropPhoto2();

                //Intent intent = new Intent(this, CreateActivity.class);
                //intent.putExtra("imagePath", path);
                //startActivityForResult(intent, CREATE_NEW_QUESTION);

            }else if (requestCode == CAMERA_WITH_DATA) {


                    doCropPhoto2();

                // TODO insert new Question to myQuestion

            }

        } else if (responseCode == RESULT_CANCELED) {

            // Do nothing

        }
    }

    private String getPathFromCamera(Intent data) {
        File f = new File(Environment.getExternalStorageDirectory()
                .toString());
        for (File temp : f.listFiles()) {
            if (temp.getName().equals("temp.jpg")) {
                return temp.getPath();
            }
        }
        return null;
    }
    private String getPathFromGallery(Intent data) {
        Uri uri = data.getData();

        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }




}
