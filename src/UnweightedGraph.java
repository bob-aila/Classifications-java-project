import java.util.*;



public class SaujanyaUnweightedGraph<V> extends SaujanyaGraph<V> {

 protected List<V> vertices = new ArrayList<>(); 

 protected List<List<SaujanyaEdge>> neighbors 

  = new ArrayList<>();

 protected SaujanyaUnweightedGraph() {

 }

 protected SaujanyaUnweightedGraph(V[] vertices, int[][] edges) {

  for (int i = 0; i < vertices.length; i++)

   addVertex(vertices[i]);

   

  createAdjacencyLists(edges, vertices.length);

 }

 protected SaujanyaUnweightedGraph(List<V> vertices, List<SaujanyaEdge> edges) {

  for (int i = 0; i < vertices.size(); i++)

   addVertex(vertices.get(i));

     

  createAdjacencyLists(edges, vertices.size());

 }

  

@SuppressWarnings("unchecked")

protected SaujanyaUnweightedGraph(List<SaujanyaEdge> edges, int numberOfVertices) {

  for (int i = 0; i < numberOfVertices; i++) 

   addVertex((V)(Integer.valueOf(i))); 

   

  createAdjacencyLists(edges, numberOfVertices);

 }

 @SuppressWarnings("unchecked")

protected SaujanyaUnweightedGraph(int[][] edges, int numberOfVertices) {

  for (int i = 0; i < numberOfVertices; i++) 

   addVertex((V)(Integer.valueOf(i))); 

 

  createAdjacencyLists(edges, numberOfVertices);

 }

 private void createAdjacencyLists(

   int[][] edges, int numberOfVertices) {

  for (int i = 0; i < edges.length; i++) {

   addEdge(edges[i][0], edges[i][1]);

  }

 }

 private void createAdjacencyLists(

   List<SaujanyaEdge> edges, int numberOfVertices) {

  for (SaujanyaEdge edge: edges) {

   addEdge(edge.u, edge.v);

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

  List<Integer> result = new ArrayList<>();

  for (SaujanyaEdge e: neighbors.get(index))

   result.add(e.v);

   

  return result;

 }



 @Override 

 public int getDegree(int v) {

  return neighbors.get(v).size();

 }



 @Override 

 public void printEdges() {

  for (int u = 0; u < neighbors.size(); u++) {

   System.out.print(getVertex(u) + " (" + u + "): ");

   for (SaujanyaEdge e: neighbors.get(u)) {

    System.out.print("(" + getVertex(e.u) + ", " +

     getVertex(e.v) + ") ");

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

   neighbors.add(new ArrayList<SaujanyaEdge>());

   return true;

  }

  else {

   return false;

  }

 }



 @Override  

 public boolean addEdge(SaujanyaEdge e) {

  if (e.u < 0 || e.u > getSize() - 1)

   throw new IllegalArgumentException("No such index: " + e.u);



  if (e.v < 0 || e.v > getSize() - 1)

   throw new IllegalArgumentException("No such index: " + e.v);

   

  if (!neighbors.get(e.u).contains(e)) {

   neighbors.get(e.u).add(e);

   return true;

  }

  else {

   return false;

  }

 }

  

 @Override  

 public boolean addEdge(int u, int v) {

  return addEdge(new SaujanyaEdge(u, v));

 }

  

 @Override 

 public SearchTree dfs(int v) {

  List<Integer> searchOrder = new ArrayList<>();

  int[] parent = new int[vertices.size()];

  for (int i = 0; i < parent.length; i++)

   parent[i] = -1;

  boolean[] isVisited = new boolean[vertices.size()];

  dfs(v, parent, searchOrder, isVisited);

  return new SearchTree(v, parent, searchOrder);

 }

 private void dfs(int v, int[] parent, List<Integer> searchOrder,

   boolean[] isVisited) {

  searchOrder.add(v);

  isVisited[v] = true; 



  for (SaujanyaEdge e : neighbors.get(v)) { 

   int w = e.v; 

   if (!isVisited[w]) { 

    parent[w] = v; 

    dfs(w, parent, searchOrder, isVisited); 

   }

  }

 }



 @Override 

 public SearchTree bfs(int v) {

  List<Integer> searchOrder = new ArrayList<>();

  int[] parent = new int[vertices.size()];

  for (int i = 0; i < parent.length; i++)

   parent[i] = -1;



  java.util.LinkedList<Integer> queue =

   new java.util.LinkedList<>(); 

  boolean[] isVisited = new boolean[vertices.size()];

  queue.offer(v);

  isVisited[v] = true; 



  while (!queue.isEmpty()) {

   int u = queue.poll(); 

   searchOrder.add(u); 

   for (SaujanyaEdge e: neighbors.get(u)) { 

    int w = e.v;

    if (!isVisited[w]) { 

     queue.offer(w); 

     parent[w] = u; 

     isVisited[w] = true; 

    }

   }

  }



  return new SearchTree(v, parent, searchOrder);

 }

 public class SearchTree {

  private int root; 

  private int[] parent; 

  private List<Integer> searchOrder; 



   

  public SearchTree(int root, int[] parent, 

    List<Integer> searchOrder) {

   this.root = root;

   this.parent = parent;

   this.searchOrder = searchOrder;

  }



   

  public int getRoot() {

   return root;

  }



 

  public int getParent(int v) {

   return parent[v];

  }



  

  public List<Integer> getSearchOrder() {

   return searchOrder;

  }





  public int getNumberOfVerticesFound() {

   return searchOrder.size();

  }

   

   

  public List<V> getPath(int index) {

   ArrayList<V> path = new ArrayList<>();



   do {

    path.add(vertices.get(index));

    index = parent[index];

   }

   while (index != -1);



   return path;

  }



  

  public void printPath(int index) {

   List<V> path = getPath(index);

   System.out.print("A path from " + vertices.get(root) + " to " +

    vertices.get(index) + ": ");

   for (int i = path.size() - 1; i >= 0; i--)

    System.out.print(path.get(i) + " ");

  }



   

  public void printTree() {

   System.out.println("Root is: " + vertices.get(root));

   System.out.print("Edges: ");

   for (int i = 0; i < parent.length; i++) {

    if (parent[i] != -1) {

     System.out.print("(" + vertices.get(parent[i]) + ", " +

      vertices.get(i) + ") ");

    }

   }

   System.out.println();

  }

 }

  

 @Override  

 public boolean remove(V v)

 {

	  

	  

	return false;}



 @Override  

 public boolean remove(int u, int v) {

 for(int i=0; i<neighbors.get(u).size(); i++)

	  

	 if(neighbors.get(u).get(i).v==v) {

		 neighbors.get(u).remove(i);

		 return true;

	 }

	 return false; 

  



	

}

public void print SaujanyaWeightedEdges() {

	// TODO Auto-generated method stub

	

}

}

public class SaujanyaWeightedEdge extends SaujanyaEdge

  implements Comparable<SaujanyaWeightedEdge> {

 public double weight; // The weight on edge (u, v)



 /** Create a weighted edge on (u, v) */

 public SaujanyaWeightedEdge(int u, int v, double weight) {

  super(u, v);

  this.weight = weight;

 }



 @Override /** Compare two edges on weights */

 public int compareTo(SaujanyaWeightedEdge edge) {

  if (weight > edge.weight) {

   return 1;

  }

  else if (weight == edge.weight) {

   return 0;

  }

  else {

   return -1;

  }

 }

}

