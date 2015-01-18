package adapter;

/**
 * Created by jason on 2015/1/18.
 */
import android.content.Context;
import android.net.Uri;
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

        // Load image
        ImageView photoImg = (ImageView) view.findViewById(R.id.img_photo);
        photoImg.setImageURI(Uri.fromFile(new File(clothes.getPath())));
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
        String description = clothes.getColor()+","+clothes.getUse();
            StringBuilder desStr = new StringBuilder(description);
            desTxt.setText(desStr.toString());
    }

}
