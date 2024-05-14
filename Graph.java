
package java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


public class Graph {
    String start;
    ArrayList<String> nodes;
    ArrayList<Edge> edges;

    public Graph(String start) {
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
    public  Graph adjacencyMatrixToGraph(ArrayList<ArrayList<Double>> arr, ArrayList<String> keys){
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
    public Boolean visted(Graph graph, ArrayList<Boolean> prev, String node){
        for(int i = 0; i < graph.nodes.size(); i++){
            if(graph.nodes.get(i) == node){
                return (prev.get(i));
            }
        }
    }
    
    public static void kosarajusAlgorithm(Graph graph){
            ArrayList<Boolean> prev = new ArrayList<Boolean>(graph.nodes.size());
            Arrays.fill(prev, Boolean.FALSE);
            private void dfs (Graph graph, ArrayList<Boolean> prev){
                Stack<Edge> s= new Stack<Edge>();
                for(int i = 0; i < graph.nodes.size(); i++){
                    String node = graph.nodes.get(i);
                    for(int e = 0; e < graph.edges.size(); e++){
                        if(graph.edges.get(e).from == node){
                            s.push(graph.edges.get(e));
                        }
                    }
                }
                while (!s.isEmpty()) {
                    Edge current = s.pop();
                    if(!this.visted(graph, prev, current.to)){

                    }
                    
                } 
            }
        }

    }
}
