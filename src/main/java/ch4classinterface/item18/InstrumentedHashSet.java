package ch4classinterface.item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;


// Broken - Inappropriate use of inheritance (Page 87)
public class InstrumentedHashSet<E> extends HashSet<E> {
  // The number of attempted element insertions
  private int addCount = 0;

  public InstrumentedHashSet() {

  }

  public InstrumentedHashSet(int initCap, float loadFactor) {
    super(initCap, loadFactor);
  }

  @Override
  public boolean add(E e)
  {
    addCount++;
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c)
  {
    addCount += c.size();
    return super.addAll(c);
  }

  public static void main(String[] args) {
    InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
    s.addAll(List.of("Snap", "Crack", "Pop"));
    System.out.println();
  }
}
