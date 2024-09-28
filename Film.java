import java.util.HashMap;

public class Film{
    String titleId;
    String title;
    double rating;
    HashMap<String, Actor> neighbouringActors;
    public Film(String tt, String t, double r){
        titleId = tt;
        title = t;
        rating = r;
        neighbouringActors = new HashMap<>();
    }


    public String getId(){
        return titleId;
    }

    public void addActor(Actor a){
        neighbouringActors.put(a.getNameID(), a);
    }

    public String toString(){
        return title;
    }
    
}
