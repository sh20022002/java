
import java.util.*;

public class EdgeGraph {
    private ArrayList<String> nodes;
    private ArrayList<Edge> edges;

    public EdgeGraph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNode(String node) {
        if (!nodes.contains(node)) nodes.add(node);
    }

    public void addEdge(String from, String to, double weight) {
        edges.add(new Edge(from, to, weight));
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<String, List<String>> buildAdjacencyList() {
        Map<String, List<String>> adj = new HashMap<>();
        for (String node : nodes) adj.put(node, new ArrayList<>());
        for (Edge edge : edges) adj.get(edge.from).add(edge.to);
        return adj;
    }

    public EdgeGraph reverseGraph() {
        EdgeGraph reversed = new EdgeGraph();
        for (String node : nodes) reversed.addNode(node);
        for (Edge edge : edges) reversed.addEdge(edge.to, edge.from, edge.weight);
        return reversed;
    }

    public List<List<String>> kosarajuSCC() {
        Map<String, List<String>> adj = buildAdjacencyList();
        Set<String> visited = new HashSet<>();
        Stack<String> finishStack = new Stack<>();

        for (String node : nodes) {
            if (!visited.contains(node)) dfs(node, adj, visited, finishStack);
        }

        EdgeGraph reversed = reverseGraph();
        Map<String, List<String>> revAdj = reversed.buildAdjacencyList();

        visited.clear();
        List<List<String>> components = new ArrayList<>();

        while (!finishStack.isEmpty()) {
            String node = finishStack.pop();
            if (!visited.contains(node)) {
                List<String> component = new ArrayList<>();
                dfsCollect(node, revAdj, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    private void dfs(String node, Map<String, List<String>> adj, Set<String> visited, Stack<String> finishStack) {
        visited.add(node);
        for (String neighbor : adj.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, adj, visited, finishStack);
            }
        }
        finishStack.push(node);
    }

    private void dfsCollect(String node, Map<String, List<String>> adj, Set<String> visited, List<String> component) {
        visited.add(node);
        component.add(node);
        for (String neighbor : adj.get(node)) {
            if (!visited.contains(neighbor)) {
                dfsCollect(neighbor, adj, visited, component);
            }
        }
    }

    static class Edge {
        String from;
        String to;
        double weight;

        public Edge(String from, String to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        EdgeGraph graph = new EdgeGraph();
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "A", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("D", "E", 1);

        for (String node : List.of("A", "B", "C", "D", "E")) graph.addNode(node);

        List<List<String>> scc = graph.kosarajuSCC();
        System.out.println("Strongly Connected Components:");
        for (List<String> component : scc) {
            System.out.println(component);
        }
    }
}