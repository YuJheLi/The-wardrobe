package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jason.mywardrobe.R;

import java.util.ArrayList;

import model.Clothes;
import model.Matches;

/**
 * Created by Jack 李宇哲 on 2015/1/21.
 */
@SuppressWarnings("ALL")
public class CollectAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Matches> matchesList;

    private LayoutInflater inflater;
    public CollectAdapter( ArrayList<Matches> matches, Context context) {


        matchesList=matches;
        Log.i("hck", matchesList.size() + "lenght");




        this.context = context;
    }
    @Override
    public int getCount() {
        return matchesList.size();
    }

    @Override
    public Matches getItem(int position) {
        return matchesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.match_item, parent, false);
        }

        fillView(convertView, position);

        return convertView;






    }

    private void fillView(View view, final int position) {

        Matches match = getItem(position);

        // Load image
        ImageView photoImg = (ImageView) view.findViewById(R.id.match_view_cloth);
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.t_shirt_yellow);
        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.pants);

        Bitmap myImg = BitmapFactory.decodeFile(match.getClothes_path());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        Drawable[] array = new Drawable[4];

        ShapeDrawable back = new ShapeDrawable();
        back.getPaint().setColor(Color.LTGRAY);
        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(Color.GREEN);
        array[0]=back;
        array[1]=border;
        array[2] = new BitmapDrawable(rotated);
        array[3] = new BitmapDrawable(bitmap3);
        LayerDrawable la=null;
        la= new LayerDrawable(array);
        la.setLayerInset(0, 0, 0, 0, 0);

        la.setLayerInset(1, 15, 10, 30, 0);
        la.setLayerInset(2, 20, 30, 40, 5);
        la.setLayerInset(3, 0, 0, 350, 350);

        photoImg.setImageDrawable(la);

        ImageView photoImg2 = (ImageView) view.findViewById(R.id.match_view_pants);

        Bitmap myImg2 = BitmapFactory.decodeFile(match.getPants_path());
        Matrix matrix2 = new Matrix();
        matrix2.postRotate(0);
        Bitmap rotated2 = Bitmap.createBitmap(myImg2, 0, 0, myImg2.getWidth(), myImg2.getHeight(),
                matrix, true);

        Drawable[] array2 = new Drawable[4];



        ShapeDrawable border2 = new ShapeDrawable();
        border2.getPaint().setColor(Color.GREEN);
        array2[0]=back;
        array2[1]=border2;
        array2[2] = new BitmapDrawable(rotated2);
        array2[3] = new BitmapDrawable(bitmap4);
        LayerDrawable la2=null;
        la2= new LayerDrawable(array2);

        la2.setLayerInset(0, 0, 0, 0, 0);
        la2.setLayerInset(1, 15, 0, 30, 10);
        la2.setLayerInset(2, 20, 5, 40, 30);
        la2.setLayerInset(3,0, 0, 350, 350);
        photoImg2.setImageDrawable(la2);





    }
    private LayerDrawable getView(int position) {

        Matches match = getItem(position);
        Bitmap myImg = BitmapFactory.decodeFile(match.getClothes_path());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        //Bitmap bitmap = ((BitmapDrawable)context.getResources().getDrawable(image[post])).getBitmap();
        Bitmap bitmap2=null;

        LayerDrawable la=null;
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.t_shirt_yellow);


            Drawable[] array = new Drawable[2];

            ShapeDrawable border = new ShapeDrawable();
            border.getPaint().setColor(Color.LTGRAY);
            array[0]=border;
            array[1] = new BitmapDrawable(rotated);
            la= new LayerDrawable(array);

            la.setLayerInset(0, 0, 0, 0, 0);
            la.setLayerInset(1, 20, 20, 20, 20);

        return la;
    }
    private LayerDrawable getView2(int position) {

        Matches match = getItem(position);
        Bitmap myImg = BitmapFactory.decodeFile(match.getPants_path());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        //Bitmap bitmap = ((BitmapDrawable)context.getResources().getDrawable(image[post])).getBitmap();
        Bitmap bitmap2=null;

        LayerDrawable la=null;


        Drawable[] array = new Drawable[2];

        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(Color.LTGRAY);
        array[0]=border;
        array[1] = new BitmapDrawable(rotated);
        la= new LayerDrawable(array);

        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 20, 20, 20, 20);

        return la;
    }




    public void dataset(ArrayList<Matches> box)
    {
        matchesList=box;
        this.notifyDataSetChanged();
    }
}
