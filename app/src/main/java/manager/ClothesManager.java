package manager;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Clothes;

/**
 * Created by Jack 李宇哲 on 2015/1/17.
 */
public class ClothesManager {


    private List<Clothes> Wardrobe;
    private String paraent_path;
    private Gson gson;
    public ClothesManager() throws IOException {
        gson=new Gson();

        File savecloth=new File(Environment.getExternalStorageDirectory(),"MyWardrobe"+File.separator+"cloth.txt");


        Type listOfTestObject = new TypeToken<ArrayList<Clothes>>(){}.getType();
        if(!savecloth.exists())
        {   savecloth.getParentFile().mkdirs();
            try {
                savecloth.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paraent_path=savecloth.getPath();
        Reader isReader = new InputStreamReader( new FileInputStream((savecloth.getPath()) ) );

        Wardrobe = Collections.synchronizedList(
                (ArrayList<Clothes>) gson.fromJson(isReader, listOfTestObject)
        );
        isReader.close();

    }
    public void addclothes(Clothes cloth){
        Wardrobe.add(cloth);



    }
    public void saveall() throws IOException {
        Writer osWriter = new OutputStreamWriter( new FileOutputStream(paraent_path));

        gson.toJson(Wardrobe, osWriter);
        osWriter.close();


    }
    public ArrayList<Clothes> getWardrobe() {
        return (ArrayList)Wardrobe;
    }







}
