import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.internal.TextListener;
import static org.junit.Assert.*;


/*
 * How to run:
 *
 * javac -cp "../lib/*" *.java && java -cp "../lib/*:." StarterTest
 *
*/

public class StarterTest {
  public static void main(String[] args) {
    System.out.println("hello test world");
    //org.junit.runner.JUnitCore.runClasses(StarterTest.class);
    JUnitCore runner = new JUnitCore();
    runner.addListener(new TextListener(System.out));
    Result result = runner.run(StarterTest.class);
    System.out.println(String.format("%d tests run in %d msec. %s failures.\n\n", result.getRunCount(), result.getRunTime(), result.getFailureCount()));
  }

  Starter s;
  @Before
  public void setup() {
    s = new Starter();
  }

  @Test
  public void ensureDefaultHelloIsReturned() {
    assertEquals(s.returnHello(), "hello world default");
  }

  @Test
  public void ensureHelloWithStringIsReturned() {
    assertEquals(s.returnHello("xy"), "hello world xy");
  }

  @Test(expected=NullPointerException.class)
  public void ensureExceptionOnNPE() {
    assertEquals(s.returnHello(null), "hello world xy");
  }
}
