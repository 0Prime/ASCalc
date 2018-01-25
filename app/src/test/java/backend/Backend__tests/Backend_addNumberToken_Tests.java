package backend.Backend__tests;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.example.prime.ascalc.Backend;

import static backend.Backend__tests.TFact.Tester.addsToLast;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;


public class Backend_addNumberToken_Tests {
  @Test
  public void test1() {
    List<String> tokens = Collections.emptyList();

    Consumer<String> test = token ->
        assertThat("empty tokens list accepts number token",
            Backend.addNumberToken(token, tokens),
            CoreMatchers.equalTo(Collections.singletonList(token)));

    test.accept("0");
    test.accept("1");
    test.accept("2");
    test.accept("3");
    test.accept("4");
    test.accept("5");
    test.accept("6");
    test.accept("7");
    test.accept("8");
    test.accept("9");
  }


  private final TFact factory = new TFact(Backend::addNumberToken);


  @Test
  public void test2() {
    final TFact.Tester t = factory.make(TFact.Tester.notEmptyTokens + TFact.Tester.lastTokenIs0 + TFact.Tester.replacesLast);
    t.test("1", asList("0"), asList("1"));
    t.test("1", asList("1", "+", "0"), asList("1", "+", "1"));
  }


  @Test
  public void test3() {
    final TFact.Tester t = factory.make(TFact.Tester.notEmptyTokens + TFact.Tester.lastTokenIsNot0 + addsToLast);
    t.test("1", asList("1"), asList("11"));
    t.test("1", asList("2"), asList("21"));
    t.test("1", asList("1", "1"), asList("1", "11"));
    t.test("1", asList("1", "+", "1"), asList("1", "+", "11"));
  }


  @Test
  public void test4() {
    final TFact.Tester t = factory.make(TFact.Tester.notEmptyTokens + TFact.Tester.lastTokenIsNotNumber + TFact.Tester.concatsTokens);
    t.test("1", asList("1", "+"), asList("1", "+", "1"));
    t.test("13.1", asList("42", "/"), asList("42", "/", "13.1"));
  }
}
