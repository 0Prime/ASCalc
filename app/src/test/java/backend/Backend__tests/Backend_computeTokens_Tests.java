package backend.Backend__tests;

import com.example.prime.ascalc.Backend;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class Backend_computeTokens_Tests {
  @Test
  public void test1() {
    List<String> tokens = Arrays.asList("1", "+", "0");
    assertThat("1 + 0 = 1", Backend.computeTokens(tokens), is("1.0"));
  }


  @Test
  public void test2() {
    List<String> tokens = Arrays.asList("1", "+", "1");
    assertThat("1 + 1 = 2", Backend.computeTokens(tokens), is("2.0"));
  }


  @Test
  public void test3() {
    List<String> tokens = Arrays.asList("40", "+", "2");
    assertThat("40 + 2 = 42", Backend.computeTokens(tokens), is("42.0"));
  }


  @Test
  public void test4() {
    List<String> tokens = Arrays.asList("1", "-", "1");
    assertThat("1 - 1 = 0", Backend.computeTokens(tokens), is("0.0"));
  }


  @Test
  public void test5() {
    List<String> tokens = Arrays.asList("2", "*", "3");
    assertThat("2 * 3 = 6", Backend.computeTokens(tokens), is("6.0"));
  }


  @Test
  public void test6() {
    List<String> tokens = Arrays.asList("10", "/", "2");
    assertThat("10 / 2 = 5", Backend.computeTokens(tokens), is("5.0"));
  }


  @Test
  public void test7() {
    List<String> tokens = Arrays.asList("1", "+", "2", "+", "3");
    assertThat("1 + 2 + 3 = 6", Backend.computeTokens(tokens), is("6.0"));
  }


  @Test
  public void test8() {
    List<String> tokens = Arrays.asList("1", "+", "2", "-", "4");
    assertThat("1 + 2 - 4 = -1", Backend.computeTokens(tokens), is("-1.0"));
  }

  @Test
  public void test9() {
    List<String> tokens = Arrays.asList("6", "/", "4");
    assertThat("6 / 4 = 1.5", Backend.computeTokens(tokens), is("1.5"));
  }
}
