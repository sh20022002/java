package java;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    String start;
    List<String> nodes;
    List<Edge> edges;

    public Graph(String start) {
        this.start = start;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
    public void addEdge(String from, String to, double weight){
        edges.add(new Edge(from, to, weight));
    }

    

    class Edge {
        String from;
        String to;
        double weight;
        

        public Edge(String from, String to, double weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

    
    }

    
    public Graph convertAddjacncyToGraph(ArrayList<ArrayList<Double>> arr, ArrayList<String> keys, String type){
        // accepts addjacany metrix and an arry of keys with the name of the nodes as the values and orderd in accordencs with 
        // the indexes of the metrix
        // returns the graph object
        
        for(int line = 0; line < arr.size(); line++){
            this.nodes.add(keys.get(line));
            for(int index = 1; index < arr.get(line).size(); index ++){
                this.addEdge(keys.get(line), keys.get(index), arr.get(line).get(index));
            }

        }
        return this;

    }
}
