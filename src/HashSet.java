import java.util.*;



public class HashSet<E> implements Collection<E> {

 

 private final static int DEFAULT_INITIAL_CAPACITY = 4;

 private final static int MAXIMUM_CAPACITY = 1 << 30; 

 private int capacity;

 private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 

 private float loadFactorThreshold; 

  private int size = 0; 

 private LinkedList<E>[] table;

 public HashSet() {  

  this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);   

 }

 public HashSet(int initialCapacity) { 

  this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);   

 }

 @SuppressWarnings("unchecked")

public HashSet(int initialCapacity, float loadFactorThreshold) { 

  if (initialCapacity > MAXIMUM_CAPACITY)

   this.capacity = MAXIMUM_CAPACITY;

  else

   this.capacity = trimToPowerOf2(initialCapacity);

   

  this.loadFactorThreshold = loadFactorThreshold;   

  table = new LinkedList[capacity];

 }

  

 @Override 

 public void clear() {

  size = 0;

  removeElements();

 }



 @Override 

 public boolean contains(Object e) {

  int bucketIndex = hash(e.hashCode());

  if (table[bucketIndex] != null) {

   LinkedList<E> bucket = table[bucketIndex]; 

   return bucket.contains(e);

  }

   

  return false;

 }

  

 @Override 

 public boolean add(E e) {

  if (contains(e)) 

   return false;

   

  if (size + 1 > capacity * loadFactorThreshold) {

   if (capacity == MAXIMUM_CAPACITY)

    throw new RuntimeException("Exceeding maximum capacity");

   

   rehash();

  }

   

  int bucketIndex = hash(e.hashCode());

  if (table[bucketIndex] == null) {

   table[bucketIndex] = new LinkedList<E>();

  }



  

  table[bucketIndex].add(e);



  size++;

  return true;

 }



 @Override

 public boolean remove(Object e) {

  if (!contains(e))

   return false;

   

  int bucketIndex = hash(e.hashCode());

  if (table[bucketIndex] != null) {

   LinkedList<E> bucket = table[bucketIndex]; 

   bucket.remove(e);

  }



  size--;

   

  return true;

 }



 @Override

 public boolean isEmpty() {

  return size == 0;

 }



 @Override 

 public int size() {

  return size;

 }



 @Override 

 public java.util.Iterator<E> iterator() {

  return new SaujanyaHashSetIterator(this);

 }

  



 private class SaujanyaHashSetIterator implements java.util.Iterator<E> {

  

  private java.util.ArrayList<E> list;

  private int current = 0; 

  private HashSet<E> set;

   

  

  public SaujanyaHashSetIterator(HashSet<E> set) {

   this.set = set;

   list = setToList();

  }



  @Override 

  public boolean hasNext() {

   return current < list.size();

  }



  @Override 

  public E next() {

   return list.get(current++);

  }



  @Override 

  public void remove() {

   set.remove(list.get(current)) ;

   list.remove(current);

   }

 }  

  

 

 private int hash(int hashCode) {

  return hashCode & (capacity - 1);

 }



 private int trimToPowerOf2(int initialCapacity) {

  int capacity = 1;

  while (capacity < initialCapacity) {

   capacity <<= 1;

  }

   

  return capacity;

 }

  

 

 private void removeElements() {

  for (int i = 0; i < capacity; i++) {

   if (table[i] != null) {

    table[i].clear();

   }

  }

 }

  

  

 @SuppressWarnings("unchecked")

private void rehash() {

  java.util.ArrayList<E> list = setToList(); 

  capacity <<= 1; 

  table = new LinkedList[capacity]; 

  size = 0; 

   

  for (E element: list) {

   add(element); 

  }

 }

 private java.util.ArrayList<E> setToList() {

  java.util.ArrayList<E> list = new java.util.ArrayList<>();

   

  for (int i = 0; i < capacity; i++) {

   if (table[i] != null) {

    for (E e: table[i]) {

     list.add(e);

    }

   }

  }  

   

  return list;

 }



 @Override

 public String toString() {

  java.util.ArrayList<E> list = setToList();

  StringBuilder builder = new StringBuilder("[");

   

   

  for (int i = 0; i < list.size() - 1; i++) {

   builder.append(list.get(i) + ", ");

  }

   

  

  if (list.size() == 0)

   builder.append("]");

  else

   builder.append(list.get(list.size() - 1) + "]");

   

  return builder.toString();

 }



 @Override

 public boolean addAll(Collection<? extends E> arg0)

 {

	  for(E a : arg0)

      add(a);

    return false;

 }



 @Override

 public boolean containsAll(Collection<?> arg0) {

	   for (Object a : arg0) {

	      boolean isContain = contains(a);

	      if (!isContain)

	      	return false;

	    }

  return true;

 }



 @Override

 public boolean removeAll(Collection<?> arg0) {

	 for (Object a : arg0) {

     remove(a);}

  return false;

 }



 @Override

 public boolean retainAll(Collection<?> arg0) {

	  

	 for (Object a : arg0) {

		 if(!arg0.contains(a))

     remove (a);}

 

  return false;

  

 }



 @Override

 public Object[] toArray() {

	 Object[] a = new Object[size()];

	  int i = 0;

	  for(E x: this) {

	    a [i] = x;

	    i++;

	  }

	   

	  return a;

 }



 @SuppressWarnings("unchecked")

@Override

 public <T> T[] toArray(T[] arg0) {

   

	 int a = 0;

	  for(E x: this)

	  {

	    arg0[a++] = (T) x;

	     

	  }

	   return arg0;	

 }

}

