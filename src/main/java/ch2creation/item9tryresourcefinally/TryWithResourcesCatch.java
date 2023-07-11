package ch2creation.item9tryresourcefinally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesCatch  {
  // try-with-resources with a catch clause  (Page 36)
  static String firstLineOfFile(String path, String defaultVal) {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      return br.readLine();
    } catch (IOException e) {
      return defaultVal;
    }
  }

  public static void main(String[] args) throws IOException {
    String path = args[0];
    System.out.println(firstLineOfFile(path, "Toppy McTopFace"));
  }
}
