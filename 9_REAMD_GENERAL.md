# Chapter 9: General Programming #

## Item 57: Minimize the scope of local variables ##
Declare local variable where it is first used.
Most local variable declaration should contain an initializer
Prefer for loops to while loops
Keep methods small focused

## Item 59: Prefer primitive types to boxed primitives
When you must use boxed primitives:
* As elements, keys and values in Collections
* As type parameters in parametrized types (Chapter 5)
* When making reflective invocations (Item 53)

In other cases prefer primitives.

## Item 60: Beware the performance of string concatenation
Using the string concatenation operator repeatedly to concatenate n strings requires time quadratic in n.
```java
	// Inappropriate use of string concatenation - Performs horribly!
	public String statement()
		String result = "";
		for (int i = 0; i < numItems(); i++)
			result += lineForItem(i);
		return result;
```
To achieve acceptable performance, use StringBuilder in place of String.
```java
	public String statement(){
		StringBuilder b = new StringBuilder(numItems() * LINE_WIDTH);
		for (int i = 0; i < numItems(); i++)
			b.append(lineForItem(i));
		return b.toString();
	}
```