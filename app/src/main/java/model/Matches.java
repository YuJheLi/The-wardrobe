package model;

import java.io.Serializable;

/**
 * Created by Jack 李宇哲 on 2015/1/21.
 */
public class Matches implements Serializable {
    private String clothes_path;
    private String pants_path;


    public Matches(String pathup,String pathdown){
        clothes_path=pathup;
        pants_path=pathdown;



    }


    public String getClothes_path() {
        return clothes_path;
    }

    public void setClothes_path(String clothes_path) {
        this.clothes_path = clothes_path;
    }

    public String getPants_path() {
        return pants_path;
    }

    public void setPants_path(String pants_path) {
        this.pants_path = pants_path;
    }
}
