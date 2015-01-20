package manager;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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


        File savecloth=new File(Environment.getExternalStorageDirectory(),"MyWardrobe"+File.separator+"clothes.txt");



        if(!savecloth.exists())
        {   savecloth.getParentFile().mkdirs();
            try {
                savecloth.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paraent_path=savecloth.getPath();
        derive();


    }
    public void derive() throws IOException {

        gson=new Gson();

        Reader isReader = new InputStreamReader( new FileInputStream(( paraent_path) ) );
        BufferedReader bufferedReader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();
        Log.d("464",json);
        //FileReader fr = new FileReader(paraent_path);

        //BufferedReader br = new BufferedReader(fr);

        //convert the json string back to object



        Type listOfTestObject = new TypeToken<ArrayList<Clothes>>(){}.getType();
        if(json.isEmpty()) {
            Wardrobe = new ArrayList<Clothes>();
        }
        else{
           Wardrobe = gson.fromJson(json, new TypeToken<ArrayList<Clothes>>() {}.getType());

        }
        try {
            isReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //new Gson().fromJson(fr, listOfTestObject);

        //BufferedReader buffered = new BufferedReader(fr);

        //list = (List<Clothes>) gson.fromJson(buffered, Clothes.class);

        //Wardrobe=new ArrayList<Clothes>();


        Log.d("Manager","readsuccess");



    }
    public void addclothes(Clothes cloth) throws IOException {
        Log.d("Manager", "");
        Wardrobe.add(cloth);
        Log.d("Manager","addsuccess");
        saveall();
        Log.d("Manager","save_success");



    }
    public void saveall() throws IOException {
        Writer osWriter = new OutputStreamWriter( new FileOutputStream(paraent_path));

        gson.toJson(Wardrobe, osWriter);
        Log.d("saveall","save_success");

        osWriter.close();


    }
    public ArrayList<Clothes> getWardrobe() {
        return (ArrayList)Wardrobe;
    }
    public void delete_cloth(){


    }







}
