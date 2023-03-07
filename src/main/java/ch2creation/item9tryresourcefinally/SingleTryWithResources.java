package ch2creation.item9tryresourcefinally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SingleTryWithResources {
  // try-with-resources - the the best way to close resources!  (Page 35)
  static String firstLineOfFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(
            new FileReader(path))) {
      return br.readLine();
    }
  }

  public static void main(String[] args) throws IOException {
    String path = args[0];
    System.out.println(firstLineOfFile(path));
  }
}