# Chapter 8: Method #

## Item 50: Make defensive copies when needed methods ##
You must program defensively, with the assumption that clients of your class will do their best to destroy its invariants.
```java

	//Broken "immutable" time period
	public final class Period{
		private final Date start;
		private final Date end;
		/**
		* @param start the beginning of the period
		* @param end the end of the period; must not precede start;
		* @throws IllegalArgumentException if start is after end
		* @throws NullPointerException if start or end is null
		*/
		public Period(Date start, Date end) {
			if(start.compare(end) > 0)
				throw new IllegalArgumentException(start + " after " + end );
			this.start = start;
			this.end = end;
		}

		public Date start(){
			return start;
		}

		public Date end(){
			return end;
		}
		...
	}
```
First Attack. Because the client keep a copy (pointer) of the parameter, it can always change it after the constructor.
```java
	Date start = new Date();
	Date end = new Date();
	Period p = new Period(start, end);
	end.setYear(78)// Modifies internal of p!
```
__Make a defensive copy of each mutable parameter to the constructor.__
```java
	public Period(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());
		if(start.compare(end) > 0)
			throw new IllegalArgumentException(start + " after " + end );
		}
```
Do not use clone method to make a defensive copy of a parameter whose type is subclass-able by untrusted parties.

Second Attack. Because the accessors returns the object used in the Period class, the client can change its value without passing the constrains.
```java
	Date start = new Date();
	Date end = new Date();
	Period p = new Period(start, end);
	p.end.setYear(78)// Modifies internal of p!
```
__Return defensive copies of mutable internal fields.__
```java
	public Date start(){
		return new Date(start.getTime());
	}

	public Date end(){
		return new Date(end.getTime());
	}
```
Preferable is to use immutable objects(Item 15)

## Item 54: Return empty collections or arrays, not null ##
There is no reason ever to return null from an array- or collection-valued method instead of returning an empty array or collection

Return an immutable empty array instead of null
```java
	// The right way to return an array from a collection
	private final List<Cheese> cheesesInStock = ...;

	private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

	/**
	* @return an array containing all of the cheeses in the shop.
	*/
	public Cheese[] getCheeses() {
		return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
	}
```
In Collections __emptySet__, __emptyList__ and __emptyMap__ methods do the same job.
```java
// The right way to return a copy of a collection
public List<Cheese> getCheeseList() {
  if (cheesesInStock.isEmpty())
    return Collections.emptyList(); // Always returns same list
  else
    return new ArrayList<Cheese>(cheesesInStock);
}
```
## Item 55: Return optionals judiciously ##
An `Optional` returning method is more flexible and easier to use than one that throws an exception, and it is less error-prone than one returns null.

```java
  // Returns maximum value in collection - throws exception if empty
  public static <E extends Comparable<E> E max(Collection<E> c) {
    if (c.isEmpty())
      throw new IllegalArgumentException("Empty collection");
    
    E result = null;
    for(E e : c)
      if (result == null || e.compareTo(result) > 0)
        result = Objects.requireNonNull(e);
    
    return result;
}
```
Altenate approache
```java
	// Returns maximum value in collection - throws exception if empty
  public static <E extends Comparable<E> Optional<E> max(Collection<E> c) {
    if (c.isEmpty())
      return Optional.empty();

    E result = null;
    for(E e : c)
      if (result == null || e.compareTo(result) > 0)
        result = Objects.requireNonNull(e);

    return Optional.of(result);
  }
```
**Never return a null value from an Optional-returning method**
```java
// Using an optional to provide a chosen default value
String lastWordInLexicon = max(words).orElse("No workds...");
```
```java
// Using an optional to throw a chosen exception
Toy myToy = max(toy).orElseThrow(TemperTantrumException::new);
```
You should never return an optional of a boxed primitive type
Do not use optional as key, value, or element in a collection or array
