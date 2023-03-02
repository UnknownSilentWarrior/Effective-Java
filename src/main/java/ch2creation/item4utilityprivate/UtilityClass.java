package ch2creation.item4utilityprivate;


// Noninstantiable utility class (Page 19)
public class UtilityClass {
  // Suppress default constructor for noninstantiability
  private UtilityClass() {
    throw new AssertionError();
  }

  // Remainder omitted
}