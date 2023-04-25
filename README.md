# Effective Java - 3rd Edition #

## Item 1 - Consider static factory methods instead of constructors ##
Example of static factory method:
```java
public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FLASE;
}
```
* (PRO) static factory have names, unlike constructors
* (PRO) static factories are not required to create a new object on each invocation
* (CON) classes w/o public/protected constructors cannot be subclasses
* ```from()```
* ```of()```
* ```valueOf()```
* ```instanc()``` or ```getInstance()```
* ```getType()```
* ```newType()```
* ```type()```

## Item 2 - Consider a builder when faced with many constructor params ##
* almost always start with a builder in the first place
  * especially so if we have more hant a handful params
  * client code much easier to read and write than telescoping constructors
  * builder are much safer than JavaBean
  
## Item 6 - Avoid creating unnecessary objects ##
* creating unnecessary objects can be avoided by using static factory method 
* some object creations are much more expensive than others
    * it may be advisable to cache such object
    * example: regex matching
* immutable objects can trivially be reused

## Item 7 - Eliminate objsolete object reference ##
* when relying on automatic gc, be wary of leaks
  * example: a resize-array stack that doesn't null out its references on ```pop()```
* common source of leaks
  * classes that manage their own memory
  * cache
    * can be mitigated with ```WeakHashMap```
  * listeners and other callbacks
    * APIs that register callbacks but don't deregister them explicitly
    * can be mitigated with ```WeakHashMap```
    
## Item 8 - Avoid finalizers and cleaners ##
* *finalizers* are unpredictable, dangerous unnecessary
* *cleaners* are less dangerous than finalizers but still slow, unpredictables and generally nenecessary
* **never do anything time-critical in a finalizer or cleaner**
* If an exception is thrown during finalization, the finalizer leaves the class unreclaimed
* to protect non-final classes againt it - write a ```final finalize()``` that does nothing
* instead of using finalizers or cleaners , simply use ```AutoCloseable```

## Item 9 - Prefer ```try-with-resources``` to ```try-finally```
## Item 12 - Always override Hashcode when you override equals ##

## Item 18: Favor composition over intheritance ##
### Problem of Using Inheritance ###
* **Inheritance violates encapsulation**, because a subclass depends on the implementation details of its super class.
* Implemenation details of a super class:
    * can contain obscure, undocumented "self-use" e.g. ```HashSet```'s ```addAll()``` method intenrnally uses ```add()```.
    * can contain optimizations that are hard to reimplement.
    * can rely on private members that are inaccessible to subclasses.
    * are subject to change in future


### Advantages of the *Wrapper Class* pattern
* A wrapper class has **no dependencies** on the implementation details of a composed class.
* In addition to being robust, it is also **flexible**, e.g. ```ForwardingSet``` can be used with any ```Set``` implementation.
* Also known as the *Decorator* pattern

## Item 19: Design and document for inheritance or else prohibit it ##
* designing a class for inheritance is hard
* Constructors must not invoke overridable method - The superclass constructor runs before  the subclass constructor, so the overriding method in the subclass will get invoked before the subclass constructor has run
* ```Cloneable``` or ```Serializable``` interfaces are especially difficult to design for inheritance

## Item 20: Use interfaces only to define types
* **The constant interface pattern is a poor use of interface** 
```java
// DO NOT USE
public interface PhysicalConstants {
  static final double AVOGADROS_NUMBER = 6.00232;
  static final double BOLTZMANN_CONSTANT = 1.39484
}
```

## Item 23: Prefer class hierarchies to tagged classes ##
* see example [ch4classinterface.item23](https://github.com/UnknownSilentWarrior/Effective-Java/tree/main/src/main/java/ch4classinterface/item23)

## Item 24: Favor static member classes over nonstatic ##
* **If you declare a member class that does not require access to an enclossing instance, alwary put the static modifier in its declaration.** If you omit this modifier, each instance will have a hidden extraneous reference to its enclosing instance
* If a nested class needs to be visible outside of a single method or is too long to fit comfortable inside a method, use a member class. See *ch4classinsterface.ch4classinterface.item24.NestedNonStaticExample.java*
* If each instance of a member class needs reference to its enclosing instance, make it nonstatic; otherwise, make it static.
