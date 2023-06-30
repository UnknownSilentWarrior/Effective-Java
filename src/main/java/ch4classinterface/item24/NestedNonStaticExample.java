package ch4classinterface.item24;

public class NestedNonStaticExample {

  private final String name;

  public NestedNonStaticExample(String name) {
    this.name = name;
  }

  public String getName() {
    // 비정적 멤버 클래스와 바깥 클래스의 관계가 확립되는 부분
    NonStaticClass nonStaticClass = new NonStaticClass("nonStatic : ");
    return nonStaticClass.getNameWithOuter();
  }

  private class NonStaticClass {
    private final String nonStaticName;

    public NonStaticClass(String nonStaticName) {
      this.nonStaticName = nonStaticName;
    }

    public String getNameWithOuter() {    
      return nonStaticName + NestedNonStaticExample.this.getName();
    }
  }
}
