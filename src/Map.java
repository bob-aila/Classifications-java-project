public interface Map<k, v>

	{

		public void clear();

		 public boolean containsKey(k key);

		 public boolean containsValue(v value);

		 public java.util.Set<Entry<k,v>>entrySet();

		 public v get(k key);

		 public boolean isEmpty();

		 public java.util.Set<k> keyset();

		

		 

		 

		 public v put (k key, v value);

		

		 public void remove (k key) ;

		 public int size ();

		 public java.util.Set<v> values();

		 

		 public static class Entry<k, v>

		 

		 {

		 k key;

		 v value;

		 public Entry(k key, v value)

		 {

		 this.key = key;

		 this.value = value;

		 }

		 public k getKey()

		 {

			return key;

			 

		 }

		 public v getvalue()

		 {

			return value;

			 

		 }

		 @Override

		 public String toString()

		 {return "[" + key+ ", " +value +"]";		 

	}}



	



	





	}

	



