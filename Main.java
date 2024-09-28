import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph(args[0], args[1]);
        BFS bfs = new BFS(graph);
        bfs.search(args[2], args[3]);


    }
    
}
