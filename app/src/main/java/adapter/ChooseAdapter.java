package adapter;

/**
 * Created by Jack 李宇哲 on 2015/1/20.
 */

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

import com.example.jason.mywardrobe.R;

import java.util.ArrayList;

import model.Clothes;


@SuppressWarnings("deprecation")
public class ChooseAdapter extends BaseAdapter {
    private int[] image;
    private boolean isChice[];
    private Context context;
    private ArrayList<Clothes> clothesList;


    public ChooseAdapter( ArrayList<Clothes> clothes, Context context) {


        clothesList=clothes;
        Log.i("hck", clothesList.size()+"lenght");



        isChice=new boolean[clothesList.size()];
        for (int i = 0; i < clothesList.size(); i++) {
            isChice[i]=false;
        }
        this.context = context;
    }


    @Override
    public int getCount() {
        return clothesList.size();
    }


    @Override
    public Clothes getItem(int location) {
        return clothesList.get(location);
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }


    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        View view = arg1;
        GetView getView=null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_ccitem, null);
            getView = new GetView();
            getView.imageView=(ImageView) view.findViewById(R.id.image_item);
            view.setTag(getView);
        } else {
            getView = (GetView) view.getTag();
        }
        getView.imageView.setImageDrawable(getView(arg0));

        return view;
    }


    static class GetView {
        ImageView imageView;
    }



    private LayerDrawable getView(int position) {

        Clothes clothes = getItem(position);
        Bitmap myImg = BitmapFactory.decodeFile(clothes.getPath());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        //Bitmap bitmap = ((BitmapDrawable)context.getResources().getDrawable(image[post])).getBitmap();
        Bitmap bitmap2=null;

        LayerDrawable la=null;
        if (isChice[position]== true){
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_check);
        }
        if (bitmap2!=null) {
            Drawable[] array = new Drawable[3];
            ShapeDrawable border = new ShapeDrawable();
            border.getPaint().setColor(Color.GREEN);
            array[0]=border;
            array[1] = new BitmapDrawable(rotated);
            array[2] = new BitmapDrawable(bitmap2);
            la= new LayerDrawable(array);
            la.setLayerInset(0, 0, 0, 0, 0);
            la.setLayerInset(1, 20, 20, 20, 20);   
            la.setLayerInset(2, 20, 20, 300, 300);

        }
        else {
            Drawable[] array = new Drawable[2];

            ShapeDrawable border = new ShapeDrawable();
            border.getPaint().setColor(Color.LTGRAY);
            array[0]=border;
            array[1] = new BitmapDrawable(rotated);
            la= new LayerDrawable(array);

            la.setLayerInset(0, 0, 0, 0, 0);
            la.setLayerInset(1, 20, 20, 20, 20);
        }
        return la;
    }
    public void chiceState(int post)
    {
        isChice[post]=isChice[post]==true?false:true;
        this.notifyDataSetChanged();
    }
    public void alltrue(int post)
    {
        isChice[post]=true;
        this.notifyDataSetChanged();
    }
    public void allfalse(int post)
    {
        isChice[post]=false;
        this.notifyDataSetChanged();
    }



}