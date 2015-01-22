package com.example.jason.mywardrobe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import adapter.BrowseAdapter;
import manager.ClothesManager;
import manager.MatchesManager;
import model.Clothes;
import model.DataWrapper;
import model.Matches;
//import com.example.jason.mywardrobe.R;

@SuppressWarnings("ALL")
public class BrowseActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {
    private static final String TAG = "BrowseActivity";


    private OnFragmentInteractionListener mFragmentInteractionListener;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ArrayList<Clothes> resultClothes;
    private ArrayList<Clothes> matchClothes;
    private ArrayList<Clothes> searchClothes;
    private ArrayList<Clothes> clothesList;
    private ArrayList<Clothes> chooseClothes;
    private ArrayList<Clothes> deleteClothes;
    private ArrayList<Matches> matchbox;
    private Clothes delete;

    private GridView clothesListView;
    private BrowseAdapter browseAdapter;
    private MatchesManager matchesManager;

    private Button choose;
    private ImageButton search;
    private Button getall;
    private boolean multi = false;
    private static final int CHOOSE_OVER = 1;
    private static final int SEARCH_OVER = 2447;
    private static final int MATCH_OVER = 3;

    // private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        ClothesManager clothesManager = null;
        try {
            clothesManager = new ClothesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clothesList = clothesManager.getWardrobe();
        resultClothes = clothesManager.getWardrobe();
        searchClothes = clothesManager.getWardrobe();
        matchClothes = new ArrayList<Clothes>();
        deleteClothes = new ArrayList<Clothes>();

        browseAdapter = new BrowseAdapter(this, searchClothes);
        clothesListView = (GridView) findViewById(R.id.list_question);
        //LayoutInflater layoutInflater = LayoutInflater.from(this);
        clothesListView.setAdapter(browseAdapter);
        clothesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(BrowseActivity.this, "你選擇了" + (position + 1) + " 號圖片", Toast.LENGTH_SHORT).show();
                alert(searchClothes.get(position));


            }
        });

        choose = (Button) findViewById(R.id.btn_multi);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrowseActivity.this, ChooseActivity.class);

                DataWrapper data = new DataWrapper(searchClothes);
                intent.putExtra("list", data);

                startActivityForResult(intent, CHOOSE_OVER);
            }
        });
        search = (ImageButton) findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(BrowseActivity.this, SearchActivity.class);
                startActivityForResult(intent_search, SEARCH_OVER);

            }
        });
        getall = (Button) findViewById(R.id.btn_getall);
        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ClothesManager clothesManager = new ClothesManager();
                    searchClothes = clothesManager.getWardrobe();
                    browseAdapter.dataset(searchClothes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        //clothesListView.setEmptyView(view.findViewById(R.id.empty));
        // clothesListView.setOnItemClickListener(this);
    }


    public void deletedialod() {
        AlertDialog.Builder ad = new AlertDialog.Builder(BrowseActivity.this);
        ad.setTitle(R.string.dialog_delete);
        ad.setMessage(R.string.dialog_deletesure);
        ad.setPositiveButton(R.string.dialog_no, new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int i) {


            }

        });

        ad.setNegativeButton(R.string.dialog_yes, new DialogInterface.OnClickListener() { //按"否",則不執行任何操作

            public void onClick(DialogInterface dialog, int i) {
                ClothesManager clothesManager = null;
                try {
                    clothesManager = new ClothesManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    clothesManager.delete_cloth(delete);
                    for (int k = 0; k < searchClothes.size(); k++) {
                        if (searchClothes.get(k).getPath().equals(delete.getPath())) {
                            searchClothes.remove(k);
                            break;
                        }

                    }
                    for (int k = 0; k < matchClothes.size(); k++) {
                        if (matchClothes.get(k).getPath().equals(delete.getPath())) {
                            matchClothes.remove(k);
                            break;
                        }

                    }
                    browseAdapter.dataset(searchClothes);
                    matchesManager = new MatchesManager();
                    matchbox = matchesManager.getMatchbox();
                    for (int r = matchbox.size() - 1; r >= 0; r--) {
                        if (matchbox.get(r).getPants_path().equals(delete.getPath()) || matchbox.get(r).getClothes_path().equals(delete.getPath())) {
                            matchesManager.delete_match(matchbox.get(r));
                        }


                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        ad.show();
    }

    public void alert(final Clothes cloth_infor) {


        AlertDialog.Builder alertadd = new AlertDialog.Builder(
                BrowseActivity.this);
        alertadd.setTitle(R.string.dialog_infor);

        LayoutInflater factory = LayoutInflater.from(BrowseActivity.this);
        final View view = factory.inflate(R.layout.browse_dialog, null);

        ImageView dioimage = (ImageView) view.findViewById(R.id.dialog_image);
        Bitmap myImg = BitmapFactory.decodeFile(cloth_infor.getPath());
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                matrix, true);

        Drawable[] array = new Drawable[2];

        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(Color.LTGRAY);
        array[0] = border;
        array[1] = new BitmapDrawable(rotated);
        LayerDrawable la = null;
        la = new LayerDrawable(array);

        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 20, 20, 20, 20);
        dioimage.setImageDrawable(la);

        TextView text1 = (TextView) view.findViewById(R.id.dialog_name);
        text1.setText(cloth_infor.getName());
        TextView text2 = (TextView) view.findViewById(R.id.dialog_kind);
        text2.setText(cloth_infor.getKind());

        TextView text3 = (TextView) view.findViewById(R.id.dialog_color);
        text3.setText(cloth_infor.getColor());

        TextView text4 = (TextView) view.findViewById(R.id.dialog_type);
        text4.setText(cloth_infor.getType());

        TextView text5 = (TextView) view.findViewById(R.id.dialog_use);
        text5.setText(cloth_infor.getUse());

        TextView text6 = (TextView) view.findViewById(R.id.dialog_texture);
        text6.setText(cloth_infor.getTexture());


        delete = cloth_infor;
        alertadd.setView(view);
        alertadd.setPositiveButton(R.string.dialog_delete, new DialogInterface.OnClickListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setBackgroundColor(Color.GREEN);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (responseCode == RESULT_OK) {

            if (requestCode == CHOOSE_OVER) {

                //String path = getPathFromCamera(data);

                // Intent intent = new Intent(this, ChooseClothesActivity.class);
                //intent.putExtra("clothes", resultClothes);
                //startActivityForResult(intent, CREATE_NEW_QUESTION);
                Log.d(TAG, "chooseover");
                DataWrapper dw = (DataWrapper) data.getSerializableExtra("chooselist");

                Log.d(TAG, "choose success");

                chooseClothes = dw.getParliaments();

                int v = 0;
                for (int i = 0; i < chooseClothes.size(); i++) {
                    v = 1;
                    for (int j = 0; j < matchClothes.size(); j++) {
                        if (chooseClothes.get(i).getPath().equals(matchClothes.get(j).getPath())) {
                            v = 0;
                            break;
                        }
                    }
                    if (v == 1) {
                        matchClothes.add(chooseClothes.get(i));
                    }
                }


                Intent in = new Intent(BrowseActivity.this, MatchActivity.class);
                DataWrapper datatoMatch = new DataWrapper(matchClothes);
                in.putExtra("chooselist", datatoMatch);
                Log.d(TAG, "entering match");
                startActivityForResult(in, MATCH_OVER);


            } else if (requestCode == SEARCH_OVER) {
                Log.d(TAG, "inin");
                DataWrapper dw = (DataWrapper) data.getSerializableExtra("list");

                Log.d(TAG, "search success");


                searchClothes = dw.getParliaments();
                browseAdapter.dataset(searchClothes);
                //browseAdapter = new BrowseAdapter(this,  searchClothes);

                //LayoutInflater layoutInflater = LayoutInflater.from(this);
                // clothesListView.setAdapter(browseAdapter);


            } else if (requestCode == MATCH_OVER) {
                DataWrapper dw = (DataWrapper) data.getSerializableExtra("matchlist");
                matchClothes = dw.getParliaments();

            }

        } else if (responseCode == 5566) {
            if (requestCode == CHOOSE_OVER) {

                Log.d(TAG, "choosedeleteover");
                DataWrapper dw = (DataWrapper) data.getSerializableExtra("deletelist");
                deleteClothes = dw.getParliaments();
                if (deleteClothes.size() != 0) {
                    ClothesManager clothesManager = null;
                    try {
                        clothesManager = new ClothesManager();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < deleteClothes.size(); i++) {
                        try {
                            clothesManager.delete_cloth(deleteClothes.get(i));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    for (int k = searchClothes.size() - 1; k >= 0; k--) {
                        for (int j = 0; j < deleteClothes.size(); j++) {
                            if (searchClothes.get(k).getPath().equals(deleteClothes.get(j).getPath())) {
                                searchClothes.remove(k);
                                break;

                            }
                        }

                    }


                    for (int k = matchClothes.size() - 1; k >= 0; k--) {
                        for (int j = 0; j < deleteClothes.size(); j++) {
                            if (matchClothes.get(k).getPath().equals(deleteClothes.get(j).getPath())) {
                                matchClothes.remove(k);
                                break;

                            }
                        }
                    }

                    try {
                        matchesManager = new MatchesManager();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    matchbox = matchesManager.getMatchbox();
                    for (int i = 0; i < deleteClothes.size(); i++) {
                        for (int r = matchbox.size() - 1; r >= 0; r--) {
                            if (matchbox.get(r).getPants_path().equals(deleteClothes.get(i).getPath()) || matchbox.get(r).getClothes_path().equals(deleteClothes.get(i).getPath())) {

                                try {
                                    matchesManager.delete_match(matchbox.get(r));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }


                    }
                    browseAdapter.dataset(searchClothes);


                    Log.d(TAG, "delete success");


                }
            } else if (responseCode == RESULT_CANCELED) {


            }


        }
    }
}
