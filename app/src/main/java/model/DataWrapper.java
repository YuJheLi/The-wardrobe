package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jack 李宇哲 on 2015/1/21.
 */
public class DataWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Clothes> parliaments;

    public DataWrapper(ArrayList<Clothes> data) {
        this.parliaments = data;
    }

    public ArrayList<Clothes> getParliaments() {
        return this.parliaments;
    }
}
