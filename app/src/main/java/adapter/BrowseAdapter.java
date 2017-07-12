package adapter;

/**
 * Created by jason on 2015/1/18.
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
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.example.jason.mywardrobe.R;
import model.Clothes;

@SuppressWarnings("ALL")
public class BrowseAdapter extends BaseAdapter{
    private static final String TAG = BrowseAdapter.class.getSimpleName();

    private Context context;

    private LayoutInflater inflater;

    private ArrayList<Clothes> clothesList;

    public BrowseAdapter(Context context, ArrayList<Clothes> clothesList) {
        this.context = context;
        this.clothesList = clothesList;
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
            convertView = inflater.inflate(R.layout.list_item_question, parent, false);
        }

        fillView(convertView, position);

        return convertView;

    }

    private void fillView(View view, final int position) {

        Clothes clothes = getItem(position);


        Bitmap bitmap3 =null;
        if(clothes.getKind().equals(context.getResources().getStringArray(R.array.kind)[0])) {
            bitmap3 =BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.t_shirt_yellow);
        }else{
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.pants);
        }
        // Load image
        ImageView photoImg = (ImageView) view.findViewById(R.id.image_see);
        Log.d("path123", clothes.getPath());
        Bitmap myImg = BitmapFactory.decodeFile(clothes.getPath());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        Drawable[] array = new Drawable[3];

        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(Color.LTGRAY);
        array[0]=border;
        array[1] = new BitmapDrawable(rotated);
        array[2] = new BitmapDrawable(bitmap3);
        LayerDrawable la=null;
        la= new LayerDrawable(array);

        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 20, 20, 20, 20);
        la.setLayerInset(2, 0, 0, 350, 350);
        photoImg.setImageDrawable(la);
        /*
        try {
            restMgr.loadImage(restMgr.getResourceUrl(Image.class, question.getImageId()),
                    photoImg, null);

        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            restMgr.loadImage(restMgr.getResourceUrl(Image.class, question.getImageId()),
                    photoImg, null);

        } catch (Exception e){
            e.printStackTrace();
        }
*/
        // Set clues
        TextView desTxt = ((TextView) view.findViewById(R.id.txt_clue));
        String description = clothes.getTexture()+","+clothes.getUse();
            StringBuilder desStr = new StringBuilder(description);
            desTxt.setText(desStr.toString());
    }
    public void dataset(ArrayList<Clothes> box)
    {
       clothesList=box;
        this.notifyDataSetChanged();
    }

}
