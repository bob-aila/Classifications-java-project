import java.util.*;

public class WeightedGraph<V> extends SaujanyaUnweightedGraph<V> {

    public WeightedGraph() {
        super();
    }

    public WeightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public WeightedGraph(List<V> vertices, List<SaujanyaWeightedEdge> edges) {
        super(vertices, edges);
    }

    public WeightedGraph(List<SaujanyaWeightedEdge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    private void createWeightedGraph(List<V> vertices, int[][] edges) {
        this.vertices = vertices;
        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<SaujanyaEdge>());
        }
        for (int i = 0; i < edges.length; i++) {
            neighbors.get(edges[i][0]).add(new SaujanyaWeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    private void createWeightedGraph(List<V> vertices, List<SaujanyaWeightedEdge> edges) {
        this.vertices = vertices;
        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<SaujanyaEdge>());
        }
        for (SaujanyaWeightedEdge edge : edges) {
            neighbors.get(edge.u).add(edge);
        }
    }

    public double getWeight(int u, int v) throws Exception {
        for (SaujanyaEdge edge : neighbors.get(u)) {
            if (edge.v == v) {
                return ((SaujanyaWeightedEdge) edge).weight;
            }
        }
        throw new Exception("Edge does not exist");
    }

    public void printWeightedEdges() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(getVertex(i) + " (" + i + "): ");
            for (SaujanyaEdge edge : neighbors.get(i)) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + ((SaujanyaWeightedEdge) edge).weight + ") ");
            }
            System.out.println();
        }
    }

    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new SaujanyaWeightedEdge(u, v, weight));
    }

    public MST getMinimumSpanningTree() {
        return getMinimumSpanningTree(0);
    }

    public MST getMinimumSpanningTree(int startingVertex) {
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[startingVertex] = 0;
        int[] parent = new int[getSize()];
        parent[startingVertex] = -1;
        double totalWeight = 0;
        List<Integer> T = new ArrayList<>();
        while (T.size() < getSize()) {
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            if (u == -1) break;
            else T.add(u);
            totalWeight += cost[u];
            for (SaujanyaEdge e : neighbors.get(u)) {
                if (!T.contains(e.v) && cost[e.v] > ((SaujanyaWeightedEdge) e).weight) {
                    cost[e.v] = ((SaujanyaWeightedEdge) e).weight;
                    parent[e.v] = u;
                }
            }
        }
        return new MST(startingVertex, parent, T, totalWeight);
    }

    public class MST extends SearchTree {

        private double totalWeight;

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }

    public ShortestPathTree getShortestPath(int sourceVertex) {
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[sourceVertex] = 0;
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1;
        List<Integer> T = new ArrayList<>();
        while (T.size() < getSize()) {
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            if (u == -1) break;
            else T.add(u);
            for (SaujanyaEdge e : neighbors.get(u)) {
                if (!T.contains(e.v) && cost[e.v] > cost[u] + ((SaujanyaWeightedEdge) e).weight) {
                    cost[e.v] = cost[u] + ((SaujanyaWeightedEdge) e).weight;
                    parent[e.v] = u;
                }
            }
        }
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    public class ShortestPathTree extends SearchTree {

        private double[] cost;

        public ShortestPathTree(int source, int[] parent, List<Integer> searchOrder, double[] cost) {
            super(source, parent, searchOrder);
            this.cost = cost;
        }

        public double getCost(int v) {
            return cost[v];
        }

        public void printAllPaths() {
            System.out.println("All shortest paths from " + vertices.get(getRoot()) + " are:");
            for (int i = 0; i < cost.length; i++) {
                printPath(i);
                System.out.println("(cost: " + cost[i] + ")");
            }
        }
    }
}
