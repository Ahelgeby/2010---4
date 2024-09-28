import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
public class BFS {
    Graph graph;
    HashSet<String> visitedFilms;
    ArrayDeque<String> queue;
    ArrayList<String> visited;

    public BFS(Graph g){
        graph = g;
        visitedFilms = new HashSet<>();
        queue = new ArrayDeque<>();
        visited = new ArrayList<>();                        //Vil ha inn både skuespillere og filmer her i riktig rekkefølge så de skrives ut f.eks 
                                                            // Cate Blanchett -> thor:Ragnarok --> Mark Rufallo --> The avengers --> Robert Downey

        
    }

    public void search(String actor1ID, String actor2ID){
        boolean found = false;
        enqueue(actor1ID);

        Actor actor = graph.actors.get(actor1ID);
        String filmId = queue.pollFirst();
        visited.add(actor.name);
        

        //Må få lagt til en måte å oppdatere skuespilleren som blir brukt som ref for å finne neste filmobjekt å traversere gjennom
        //Tror Algoritmen funker sånn den skal: Altså finn en vei mellom 2 skuespillere via filmer og andre skuespillere, men akkuratt nå skriver den bare ut startperson,
        //Filmene den traverserer, og sluttperson

        while(queue.size() >= 0 && found == false){                                     
            Film film = graph.films.get(filmId);
            if(!visited.contains(film.title)){
                visited.add(film.title);
                if(film.neighbouringActors.containsKey(actor2ID)){
                    found = true;
                    visited.add(graph.actors.get(actor2ID).name);
                    
                }
                else{
                    for(String i: film.neighbouringActors.keySet()){
                        enqueue(i);
                    }
                }
            }
            filmId = queue.pollFirst();
        }
        for(int i= 0; i<visited.size();i++){
            System.out.println(visited.get(i));
        }
    }

    public void enqueue(String actor){
        for(String filmId: graph.actors.get(actor).neighbouringFilms.keySet()){
            queue.offer(filmId);
        }
    }

    //Todo:
    /* Ta inn to skuespillere, sjekk i skuespiller 1 sin naboliste(Dette er filmer) om filmen også inneholder skuespiller 2's nm id, hvis ikke: gå til neste nabofilm
     * legg inn forrige film sin filmID i visited så den ikke blir sjekket flere ganger. 
     * 
     */
}
