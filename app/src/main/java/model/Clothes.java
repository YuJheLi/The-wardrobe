package model;

import android.net.Uri;



/**
 * Created by Jack 李宇哲 on 2015/1/18.
 */
public class Clothes {

    private String path;
    private String type;
    private String name;
    private String texture;
    private String color;
    private String use;
    private String kind;


    public Clothes(String path1,String name1 ,String kind1,String type1,String color1,String use1,String texture1){

        path=path1;
        name=name1;
        kind=kind1;
        type=type1;
        color=color1;
        use=use1;
        texture=texture1;



    }


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
}
