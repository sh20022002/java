
package java;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Graph {
    // Existing code...

    public void kosarajusAlgorithm(Graph graph){
        ArrayList<Boolean> prev = new ArrayList<>();
        for (int i = 0; i < graph.nodes.size(); i++) {
            prev.add(Boolean.FALSE);
        }
        // Rest of the code...
    }
}

public class EdgeGraph {
    String start;
    ArrayList<String> nodes;
    ArrayList<Edge> edges;

    public EdgeGraph(String start) {
        this.start = start;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addEdge(String from, String to, double weight){
        edges.add(new Edge(from, to, weight));
        Collections.sort(edges);
    }

    public void addNode(String node){
        nodes.add(node);
        Collections.sort(nodes);
    }
    public  EdgeGraph adjacencyMatrixToGraph(ArrayList<ArrayList<Double>> arr, ArrayList<String> keys){
        for(int j = 0; j < arr.size(); j++ ){
            this.addNode(keys.get(j));
            for(int i = 0; i < arr.get(j).size(); i++ ){
                if(arr.get(j).get(i) != Double.POSITIVE_INFINITY && i != j){
                    this.addEdge(keys.get(j), keys.get(i), arr.get(j).get(i));
                }
            }

        }
        return this;
    }

    class Edge implements Comparable<Edge> {
        String from;
        String to;
        double weight;
    

        public Edge(String from, String to, double weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.from.compareTo(other.from);
        }
    }
    public Boolean visted(EdgeGraph graph, ArrayList<Boolean> prev, String node){
        for(int i = 0; i < graph.nodes.size(); i++){
            if(graph.nodes.get(i) == node){
                return (prev.get(i));
            }
        }
    }
    
    public static void kosarajusAlgorithm(EdgeGraph graph){
        ArrayList<Boolean> prev = new ArrayList<>();
        for (int i = 0; i < graph.nodes.size(); i++) {
            prev.add(Boolean.FALSE);
        }
    } // Close the dfs method here
    private void dfs(EdgeGraph graph, ArrayList<Boolean> prev) {
        int counter = graph.nodes.size() - 1;
        Stack<String> s= new Stack<String>();
        for(int i = 0; i < graph.nodes.size(); i++){
            s.push(graph.nodes.get(i));
        }
        while (!s.isEmpty() && counter >= 0){
            String current = s.pop();
            if(this.visted(graph, prev, current)){
                prev.set(counter, Boolean.TRUE);
                counter --;
            }else{
                for(int e = 0; e < graph.edges.size(); e++){
                    // needs to be used differen because needed a way to continue accessing the next node untill the edge is leading to
                    // a visted node
                }
            }
            
        } 
    }
}
