import java.util.HashMap;

public class Actor {
    String nameId;
    String name;
    String titleId;
    HashMap<String, Film> neighbouringFilms;
    public Actor(String nm, String n){
        neighbouringFilms = new HashMap<>();                //Brukt til traversering
        nameId = nm;
        name = n;
    }

    public void addFilm(Film f){
        neighbouringFilms.put(f.getId(), f);
    }
    public String getNameID(){
        return nameId;
    }

    public String toString(){
        return name;
    }
}

