# Chapter 7: Lambdas and Stream #

## Item 43: Prefer method references to lambdas ##
Java provides a way to generate function objects even more succinct than lambdas:  `method reference`
```java
// lambda
map.merger(key, 1, (count, incr) -> count + incr);

// method reference
map.merger(key, 1, Integer::sum);
```
Occasionally, a lambda will be more succinct than a method reference. This happens most often when the method is in the same class as the lambda.
```java
// method reference. Occur in a class named GoshThisClassNameIsHumongous
service.execute(GoshThisClassNameIsHumongous::action);

// lambda
service.execute(() -> action());
```
When method reference are shorter and clearer, use them; where they aren't, stick with lambdas

## Item 46: Use side-effect-free function object  ##
`forEach` should **only** be used to __report the result__ of a computation performed by a stream, __not to perform the computation__.

Side-effect-free functions:
* `toList`
* `toSet`
* `toMap`
* `groupingBy`
* `joining`
