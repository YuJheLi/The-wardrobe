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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import model.Clothes;
import model.Matches;

/**
 * Created by Jack 李宇哲 on 2015/1/21.
 */
public class MatchesManager {
    private List<Matches> Matchbox;
    private String paraent_path;
    private Gson gson;

    public MatchesManager() throws IOException {
        File savematch = new File(Environment.getExternalStorageDirectory(), "MyWardrobe" + File.separator + "matches.txt");
        if (!savematch.exists()) {
            savematch.getParentFile().mkdirs();
            try {
                savematch.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paraent_path = savematch.getPath();
        derive();




    }
    public  void derive() throws IOException {


        gson = new Gson();
        Reader isReader = new InputStreamReader(new FileInputStream((paraent_path)));
        BufferedReader bufferedReader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();

        if (json.isEmpty()) {
            Matchbox = new ArrayList<Matches>();
        } else {
            Matchbox  = gson.fromJson(json, new TypeToken<ArrayList<Matches>>() {
            }.getType());

        }

        bufferedReader.close();
        isReader.close();

    }

    public void addMatch(Matches match) throws IOException {

        Matchbox.add(match);
        saveall();
    }
    public void saveall() throws IOException {
        Writer osWriter = new OutputStreamWriter(new FileOutputStream(paraent_path));

        gson.toJson(Matchbox, osWriter);


        osWriter.close();


    }
    public ArrayList<Matches> getMatchbox() {
        ArrayList<Matches> newlist=new ArrayList<Matches>();
        for (int i = 0; i < Matchbox.size(); i++) {
            newlist.add(Matchbox.get(i));
        }



        return newlist;
    }
    public void delete_match(Matches match) throws IOException {
        for (int i = 0; i < Matchbox.size(); i++) {
            if(match.getClothes_path().equals(Matchbox.get(i).getClothes_path())&&match.getPants_path().equals(Matchbox.get(i).getPants_path())){

                Matchbox.remove(i);
                break;
            }
        }
        saveall();


    }
    public boolean isExist(Matches match){
        for (int i = 0; i < Matchbox.size(); i++) {
            if(match.getClothes_path().equals(Matchbox.get(i).getClothes_path())&&match.getPants_path().equals(Matchbox.get(i).getPants_path())){

                return true;

            }
        }
        return false;


    }



}
