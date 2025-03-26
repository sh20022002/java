
//copyed from Algorithms/src/main/java/com/williamfiset/algorithms/graphtheory
///DijkstrasShortestPathAdjacencyListWithDHeap.java
//for learning 


import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@SuppressWarnings("unused")
public class DjakrasShortestPath {

    // An Edge class to represent a directed Edge
    // betwen Nodes with weights
    public static class Edge {
        int to;
        double cost;
        public Edge(int to, double cost){
            this.to = to;
            this.cost = cost;
        }
    
        
    }
    private final int n;

    private int edgeCount;
    //not used currently
    private double[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;

    public DjakrasShortestPath(int n){
        this.n = n;
        createEmptyGraph();
    }
    // constract the empty graph
    private void createEmptyGraph(){
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    }

    public void addEdge( int from, int to, int cost){
        edgeCount++;
        graph.get(from).add(new Edge(to, cost));
    }
    public List<List<Edge>> getGraph() {
        return graph;
    }

    public double dijkstra(int start, int end){
        // indexed priority queue (ipq)
        int degree = edgeCount/ n;
        //needs to create private classs MinIndexedDheap 
        MinIndexedDHeap<Double> ipq = new MinIndexedDHeap<>(degree, n);
        ipq.insert(start, 0.0); //inserts to queue

        //minimum distance to node
        dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0.0;

        boolean[] visited = new boolean[n]; //creates a array with boolean value for each node
        prev = new Integer[n]; // array for previuos node

        while (!ipq.isEmpty()){
            int nodeId = ipq.peekMinKeyIndex(); // not ceated yet

            visited[nodeId] = true;
            double minValue = ipq.pollMinValue(); //not ceated yet

            if (minValue > dist[nodeId]) continue; //found better path
            
            // chacks all edges of node
            for (Edge edge: graph.get(nodeId)){

                //chacks if visited edge by the destanition
                if (visited[edge.to]) continue;

                //updates minimum cost
                double newDist = dist[nodeId] + edge.cost;
                if (newDist < dist[edge.to]){
                    prev[edge.to] = nodeId;
                    dist[edge.to] = newDist;

                    // Insert the cost of going to a node for the first time in the PQ,
                    // or try and update it to a better value by calling decrease.
                    //methods not created
                    if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                    else ipq.decrease(edge.to, newDist);

                }


            }
            if (nodeId == end) return dist[end]; // chacks if node is end
        }
        return Double.POSITIVE_INFINITY; // returns end node note connected

    }
    // Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
    public List<Integer> recconstructPath(int start, int end) {
        if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
        List<Integer> path = new ArrayList<>();
        double dist = dijkstra(start, end);
        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer at =end; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        return path;

    }

    private static class MinIndexedDHeap<T extends Comparable<T>> { //?

        //elments in heap
        private int sz;

        //max elements in heap
        private final int N;

        // The degree of every node in the heap.
        private final int D;

        // Lookup arrays to track the child/parent indexes of each node.
        private final int[] child, parent;

        // The Position Map (pm) maps Key Indexes (ki) to where the position of that
        // key is represented in the priority queue in the domain [0, sz).
        public final int[] pm;

        // The Inverse Map (im) stores the indexes of the keys in the range
        // [0, sz) which make up the priority queue. It should be noted that
        // 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
        public final int[] im;

        // The values associated with the keys. It is very important  to note
        // that this array is indexed by the key indexes (aka 'ki').
        public final Object[] values;

        // Initializes a D-ary heap with a maximum capacity of maxSize.
        public MinIndexedDHeap(int degree, int maxSize) {
        if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            D = max(2, degree);
            N = max(D + 1, maxSize);

            im = new int[N];
            pm = new int[N];
            child = new int[N];
            parent = new int[N];
            values = new Object[N];

            for (int i = 0; i < N; i++) {
            parent[i] = (i - 1) / D;
            child[i] = i * D + 1;
            pm[i] = im[i] = -1;
            }
        }

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public boolean contains(int ki) {
            keyInBoundsOrThrow(ki);
            return pm[ki] != -1;
        }

        public int peekMinKeyIndex() {
            isNotEmptyOrThrow();
            return im[0];
        }

        public int pollMinKeyIndex() {
            int minki = peekMinKeyIndex();
            delete(minki);
            return minki;
        }

        @SuppressWarnings("unchecked")
        public T peekMinValue() {
            isNotEmptyOrThrow();
            return (T) values[im[0]];
        }

        public T pollMinValue() {
            T minValue = peekMinValue();
            delete(peekMinKeyIndex());
            return minValue;
        }

        public void insert(int ki, T value) {
            if (contains(ki)) throw new IllegalArgumentException("index already exists; received: " + ki);
            valueNotNullOrThrow(value);
            pm[ki] = sz;
            im[sz] = ki;
            values[ki] = value;
            swim(sz++);
        }

        @SuppressWarnings("unchecked")
        public T valueOf(int ki) {
            keyExistsOrThrow(ki);
            return (T) values[ki];
        }

        @SuppressWarnings("unchecked")
        public T delete(int ki) {
            keyExistsOrThrow(ki);
            final int i = pm[ki];
            swap(i, --sz);
            sink(i);
            swim(i);
            T value = (T) values[ki];
            values[ki] = null;
            pm[ki] = -1;
            im[sz] = -1;
            return value;
        }

        @SuppressWarnings("unchecked")
        public T update(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            final int i = pm[ki];
            T oldValue = (T) values[ki];
            values[ki] = value;
            sink(i);
            swim(i);
            return oldValue;
        }

        // Strictly decreases the value associated with 'ki' to 'value'
        public void decrease(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(value, values[ki])) {
                values[ki] = value;
                swim(pm[ki]);
            }
        }

        // Strictly increases the value associated with 'ki' to 'value'
        public void increase(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(values[ki], value)) {
                values[ki] = value;
                sink(pm[ki]);
            }
        }

        /* Helper functions */

        private void sink(int i) {
            for (int j = minChild(i); j != -1; ) {
                swap(i, j);
                i = j;
                j = minChild(i);
            }
        }

        private void swim(int i) {
            while (less(i, parent[i])) {
                swap(i, parent[i]);
                i = parent[i];
            }
        }

        // From the parent node at index i find the minimum child below it
        private int minChild(int i) {
            int index = -1, from = child[i], to = min(sz, from + D);
            for (int j = from; j < to; j++) if (less(j, i)) index = i = j;
            return index;
        }

        private void swap(int i, int j) {
            pm[im[j]] = i;
            pm[im[i]] = j;
            int tmp = im[i];
            im[i] = im[j];
            im[j] = tmp;
        }

        // Tests if the value of node i < node j
        @SuppressWarnings("unchecked")
        private boolean less(int i, int j) {
            return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
        }

        @SuppressWarnings("unchecked")
        private boolean less(Object obj1, Object obj2) {
            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
        }

        @Override
        public String toString() {
            List<Integer> lst = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) lst.add(im[i]);
            return lst.toString();
        }

        /* Helper functions to make the code more readable. */

        private void isNotEmptyOrThrow() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        }

        private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
            keyExistsOrThrow(ki);
            valueNotNullOrThrow(value);
        }

        private void keyExistsOrThrow(int ki) {
            if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        }

        private void valueNotNullOrThrow(Object value) {
            if (value == null) throw new IllegalArgumentException("value cannot be null");
        }

        private void keyInBoundsOrThrow(int ki) {
            if (ki < 0 || ki >= N)
                throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
        }







    }
}