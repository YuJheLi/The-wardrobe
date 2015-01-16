package com.example.jason.mywardrobe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.Toast;

import java.io.File;


public class AddclothActivity extends ActionBarActivity {

    private static final String TAG = "AddclothActivity";
    private ImageView myImage = null;

    private static final int PHOTO_PICKED_WITH_DATA = 3021;
    private static final int CAMERA_WITH_DATA = 3023;
    private static final int IMAGE_FROM_GALLERY = 3024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcloth);

        myImage = (ImageView)findViewById(R.id.myimage);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addclothes, menu);
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
                        //File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                       // intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

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
                .getExternalStorageDirectory(), "temp.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);

    }
    protected void doCropPhoto2(Bitmap data){
        Intent intent = getCropImageIntentFor(data);
        File f = new File(android.os.Environment
                .getExternalStorageDirectory(), "temp.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);

    }



    public static Intent getCropImageIntent(Bitmap data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        intent.putExtra("data", data);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 128);
        intent.putExtra("outputY", 128);
        intent.putExtra("return-data", true);
        return intent;
    }
    public static Intent getCropImageIntentFor(Bitmap data) {
        Intent intent = new Intent("com.android.camera.action.CROP");

       // intent.setDataAndType(Uri.fromFile(tempFile), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 128);
        intent.putExtra("outputY", 128);
        intent.putExtra("return-data", true);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (responseCode == RESULT_OK) {

            if (requestCode == PHOTO_PICKED_WITH_DATA) {

               // String path = getPathFromCamera(data);
                //Log.d(TAG, path);




               // Intent intent = new Intent(this, CreateActivity.class);
                //intent.putExtra("imagePath", path);
                //startActivityForResult(intent, CREATE_NEW_QUESTION);
                Bitmap photo1 = data.getParcelableExtra("data");
                if(photo1!=null){
                    myImage.setImageBitmap(photo1);}

            } else if (requestCode == IMAGE_FROM_GALLERY) {

              // String path = getPathFromGallery(data);
                //Log.d(TAG, path);
                final Bitmap photo = data.getParcelableExtra("data");

                if(photo!=null){
                    doCropPhoto2(photo);
                }

                //Intent intent = new Intent(this, CreateActivity.class);
                //intent.putExtra("imagePath", path);
                //startActivityForResult(intent, CREATE_NEW_QUESTION);

            }else if (requestCode == CAMERA_WITH_DATA) {
                final Bitmap photo = data.getParcelableExtra("data");
                if(photo!=null){
                    doCropPhoto(photo);
                }
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
