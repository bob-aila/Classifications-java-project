import java.util.*;

public class SaujanyaGraph<V> {

    private List<V> vertices;
    private List<List<Integer>> neighbors;

    public SaujanyaGraph() {
        vertices = new ArrayList<>();
        neighbors = new ArrayList<>();
    }

    public SaujanyaGraph(V[] vertices, int[][] edges) {
        this();
        for (V vertex : vertices) {
            addVertex(vertex);
        }
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int u = 0; u < vertices.size(); u++) {
            System.out.print("Vertex " + getVertex(u) + ": ");
            for (int v : neighbors.get(u)) {
                System.out.print("(" + getVertex(u) + ", " + getVertex(v) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(int u, int v) {
        if (u >= 0 && u < getSize() && v >= 0 && v < getSize() && !neighbors.get(u).contains(v)) {
            neighbors.get(u).add(v);
            neighbors.get(v).add(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(meazaEdge e) {
        return addEdge(e.u, e.v);
    }

    @Override
    public boolean remove(V v) {
        if (vertices.contains(v)) {
            int index = vertices.indexOf(v);
            vertices.remove(index);
            neighbors.remove(index);

            for (List<Integer> neighborList : neighbors) {
                neighborList.remove((Integer) index);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int u, int v) {
        if (u >= 0 && u < getSize() && v >= 0 && v < getSize()) {
            neighbors.get(u).remove((Integer) v);
            neighbors.get(v).remove((Integer) u);
            return true;
        }
        return false;
    }

    @Override
    public meazaUnweightedGraph<V>.SearchTree dfs(int v) {
        return new meazaUnweightedGraph<>(this, v).dfs(v);
    }

    @Override
    public meazaUnweightedGraph<V>.SearchTree bfs(int v) {
        return new meazaUnweightedGraph<>(this, v).bfs(v);
    }

    @Override
    public void printmeazaWeightedEdges() {
        // Implement this method if you have a weighted graph
    }
}
