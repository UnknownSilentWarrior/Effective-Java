# Chapter 5: Generic #

## Item 30: Favor generic methods ##
* The type bound `<E extends Comparable<E>>` may be read as "any type E that can be compared to itself,"

## Item 31: Use bounded wildcards to increase API flexibility ##
Parameterized types are invariant. Ie `List<String>` is not a subtype of `List<Object>`

```java
public void pushAll(Iterable<E> src){
  for(E e : src)
    push(e)
}

// Integer is a subtype of Number
Stack<Number> numberStack = new Stack<Number>();
Iterable<Integer> integers = ...
numberStack.pushAll(integers); //Error message here: List<Integer> is not a subtype of List<Number>
```

**Bounded wildcard type**

**PECS: producer-extends, consumer-super**
Producer

```java
public void pushAll(Iterable<? Extends E> src){
  for (E e : src)
    push(e);
}
```

Consumer

```java
public void popAll(Collection<? super E> dst){
  while(!isEmpty())
    dst.add(pop());
}
```
**For maximum flexibility, use wildcard types on input parameters that represent producers or consumers**

**PECS: producer-extends, consumer-super**
In other word, if a parameterized type represents a T producer, user <? extends T>; if it represents a T consumer, user<? super T>
If the parameter is a producer and a consumer don't use _wildcards_
```java
public Chooser(Collection<? extends T> choices)
public void popAll(Collection<? super E> dst)
```
Never use _wildcards_ in return values.

Type inference in generics
```java
Set<Integer> integers =...
Set<Double> doubles =...
Set<Number> numbers = union(integers,doubles);//Error

//Needs a 'explicit type parameter'
Set<Number> numbers = Union.<Number>union(integers,doubles);
```

Comparable and Comparators are always consumers. Use `Comparable<? super T>` and `Comparator<? super T>`

If a type parameter appears only once in a method declaration, replace it with a wildcard.

## Item 33: Consider typesafe heterogeneous containers ##
A container for accessing a heterogeneous list of types in a typesafe way.

**API**
```java
public class Favorites{
  public void putFavorites(Class<T> type, T instance);
  public <T> getFavorite(Class<T> type);
}
```

**Client**
```java
Favorites f = new Favorites();
f.putFavorites(String.class, "JAVA");
f.putFavorites(Integer.class, 0xcafecace);
f.putFavorites(Class.class, Favorite.class);

String s = f.getFavorites(String.class);
int i =f.getFavorites(Integer.class);
Class<?> c = f.getFavorites(Class.class);
```

**Implementation**
```java
public class Favorites{
  private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();

  public <T> void putFavorites(Class<T> type, T instance){
    if(type == null)
	throw new NullPointerException("Type is null");
    favorites.put(type, type.cast(instance));//runtime safety with a dynamic cast
  }

  public <T> getFavorite(Class<T> type){
    return type.cast(favorites.get(type));
  }
}
```
