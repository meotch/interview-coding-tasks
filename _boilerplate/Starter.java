/*
 * How to run:
 *
 * javac -cp "../lib/*" *.java && java -cp "../lib/*:." Starter
 *
*/

public class Starter {

  public String returnHello() {
    return returnHello("default");
  }
  public String returnHello(Object arg) {
    // toString lets us test an exception
    return "hello world " + arg.toString();
  }
  public static void main(String[] args) {
    System.out.println(new Starter().returnHello());
  }

} 

