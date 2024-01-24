# Chapter 6: Enums and Annotations #

## Item 34: Use enums instead of int constants ##
Strategy enum pattern Use it, if multiple enum constants share common behaviors.

```java

enum PayrollDay{
  MONDAY(PayType.WEEKDAY),
  TUESDAY(PayType.WEEKDAY),
		...
  SATURDAY(PayType.WEEKEND),
  SUNDAY(PayType.WEEKEND);

  private final PayType payType;

  PayrollDay(PayType payType) {this.payType = payType;}

  double pay(double hoursWorked, double payRate){
    return payType.pay(hoursWorked, payRate);
  }
  //The strategy  enum type
  private enum PayType{
    WEEKDAY{
      double overtimePay(double hours, double payRate) { return ...}
    };
    WEEKEND{
      double overtimePay(double hours, double payRate) { return ...}
    };
    private static final int HOURS_PER_SHIFT = 8;

    abstract double overtimePay(double hours, double payRate);

    double pay(double hoursWorked, double payRate){
      double basePay = hoursWorked * payRate;
      return basePay + overtimePay(hoursWorked, payRate);
    }
  }
}
```
## Item 35: Use instance fields instead of ordinals ##
Never derive a value of an enum to its ordinal
```java
public enum Ensemble{
  SOLO, DUET, TRIO...;
  public int numberOfMusicians() {return ordinal() + 1}
}
```
Better approach
```java
public enum Ensemble{
  SOLO(1), DUET(2), TRIO(3)...TRIPLE_QUARTET(12);
  private final int numberOfMusicians;
  Ensemble(int size) {this.numberOfMusicians = size;}
  public int numberOfMusicians() {return numberOfMusicians;}
}
```

## Item 36: Use EnumSet instead of bit fields ##
Benefit of EnumSet
* Compactness and Efficiency: EnumSet represents the elements in a bit vector, making it very compact and efficient. It uses less memory compared to other Set implementations.
* Type Safety: EnumSet provides type safety for enumerated types. By using EnumSet, you can be sure that only the specified enum type elements will be added to the set.
* Performance: Since EnumSet uses a bit vector representation for its elements, it provides much better performance than other Set implementations when working with enumerated types.
* Implementation Specific Benefits: EnumSet's implementation using RegularEnumSet and JumboEnumSet provides many benefits
. All the methods in EnumSet are implemented using these classes and the methods are optimized for the best performance.
```java
public class Text{
  public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

  //Any Set could be passed. Best EnumSet
  public void applyStyles(Set<Style> styles){ ... }
}

//Use
  text.applyStyles(EnumSet.of(Style.BOLD, Style. ITALIC));
```

## Item 37: Use EnumMap instead of ordinal indexing ##
Benefit of EnumMap:
* EnumMap is a specialized implementation of the Map interface in Java that is designed to be used exclusively with keys that are of the enum type. The main benefit of using EnumMap is that it is highly optimized for use with enum keys, being represented internally as an array, leading to better performance than using a general-purpose Map such as HashMap. Additionally, EnumMap stores keys in the natural order of their keys , which is the order in which the enum constants are declared. This can be useful in situations where ordering is important. Overall, using EnumMap can lead to better performance and more efficient use of memory than using a general-purpose Map.
```java
// Java program to illustrate working
// of EnumMap

import java.util.*;

class EnumMapExample {

  public enum Days {
    Sunday,
    Monday,
    Tuesday,
    Wendnesday;
  }

  public static void main(String[] args)
  {
    // Creating an EnumMap of the Days enum
    EnumMap<Days, Integer> enumMap
            = new EnumMap<>(Days.class);

    // Insert using put() method
    enumMap.put(Days.Sunday, 1);
    enumMap.put(Days.Monday, 2);
    enumMap.put(Days.Tuesday, 3);
    enumMap.put(Days.Wednesday, 4);

    // Printing size of EnumMap
    System.out.println("Size of EnumMap: "
            + enumMap.size());
    // Printing the EnumMap
    for (Map.Entry m : enumMap.entrySet()) {
      System.out.println(m.getKey() + " "
              + m.getValue());
    }
  }
}
```


## Item 38:  Emulate *extensible* enums with interfaces ##
Enums types can not extend another enum types.

Opcodes as a use case of enums extensibility.
```java
public interface Operation{
  double apply(double x, double y);
}
public enum BasicOperation implements Operation{
  PLUS("+"){
    public double apply(double x, double y) {return x + y}
  },
  MINUS("-"){...},TIMES("*"){...},DIVIDE("/"){...};

  private final String symbol;
  BasicOperation(String symbol){
    this.symbol = symbol;
  }
  @Override
  public String toString(){ return symbol; }
}
```
BasicOperation is not extensible, but the interface type Operation is, and it is the one used to represent operations in APIs.
```java

public enum ExtendedOperation implements Operation{
  EXP("^"){
    public double apply(double x, double y) {return Math.pow(x,y)}
  },
  REMAINDER("%"){
    public double apply(double x, double y) {return x % y}
  }

  private final String symbol;
  ExtendedOperation(String symbol){
    this.symbol = symbol;
  }
  @Override
  public String toString(){ return symbol; }
}
```
## Item 41:  Use marker interfaces to define types ##
Marker interface in Java is interfaces with no field or methods or in simple word empty interface in java is called **marker interface**.

A marker interface is an interface that contains no method declarations, but "marks" a class that implements the interface as having some property.

When your class implements java.io.Serializable interface it becomes Serializable in Java and gives compiler an indication that use Java Serialization mechanism to serialize this object.
* Marker interfaces define a type that is implemented by instances of the marked class; marker annotations do not. (Catch errors in compile time).
* They can be targeted more precisely than marker annotations.
* It's possible to add more information to an annotation type after it is already in use.
