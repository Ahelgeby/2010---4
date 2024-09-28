import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class Graph{
    HashMap<String, Actor> actors = new HashMap<>();
    HashMap<String, Film> films = new HashMap<>();
    int numberOfMovies = 0;                                 //Brukt til telling og debugging
    int numberOfActors = 0;
    int numberOfEdges = 0;

    public Graph(String movie, String actor) throws IOException{
        Long start = System.nanoTime();
        File movies = new File(movie);
        File actors = new File(actor);

        readMovies(movies);
        readActors(actors);
        connectEdges();
        sumEdges();

        Long stop = System.nanoTime();
        Long total = (stop - start) / 1000000;
        System.out.println("Time elapsed: " + total + " millis");


    }
    public void readMovies(File moviefile) throws IOException{                          //Leser inn moviefila og legger de til i hashmap
        BufferedReader read = new BufferedReader(new FileReader(moviefile));
        String line = read.readLine();
        while(line != null){
            String[] lines = line.split("\t");
            Film film = new Film(lines[0], lines[1], Double.parseDouble(lines[2]));
            films.put(lines[0], film);
            numberOfMovies ++;
            line = read.readLine();
        }
        read.close();

    }
    public void readActors(File actorfile) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader(actorfile));           //Leser inn actors fila, putter de i egen hashmap, legger til skuespillerobjektet i filmens egen liste
        String line = read.readLine();
        while(line != null){
            String[] lines = line.split("\t");
            Actor actor = new Actor(lines[0], lines[1]);
                actors.put(lines[0], actor);
                for(int i = 2; i<lines.length; i++){
                    if(films.get(lines[i]) != null){
                        films.get(lines[i]).addActor(actor);
                    }
                }
            numberOfActors ++;
            line = read.readLine();
        }
        read.close();
    }

    public void connectEdges(){                                             //Legger til filmreferanser i skuespillerobjektene
        for(String i: films.keySet()){
            Film film = films.get(i);
            for(String a : film.neighbouringActors.keySet()){
                film.neighbouringActors.get(a).addFilm(film);
            }
        }
    }

    public void sumEdges(){
        int edges = 0;
        for(String i: films.keySet()){
            for(String a: films.get(i).neighbouringActors.keySet()){
                edges++;
            }
        }
        for(String a : actors.keySet()){
            for(int i = 0; i<actors.get(a).neighbouringFilms.size(); i++){
                edges++;
            }
        }
        System.out.println("Number of edges: " + edges);
    }
    
}