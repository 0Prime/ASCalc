package backend.Backend__tests;


import org.junit.Test;

import java.util.Collections;

import com.example.prime.ascalc.Backend;

import static java.util.Arrays.asList;


public class Backend_addOperationToken_tests {
  private final TFact factory = new TFact(Backend::addOperationToken);


  @Test
  public void test1() {
    final TFact.Tester t = factory.make(TFact.Tester.emptyTokens + TFact.Tester.notChanged);
    t.testNotChanged("+", Collections.emptyList());
    t.testNotChanged("-", Collections.emptyList());
    t.testNotChanged("/", Collections.emptyList());
    t.testNotChanged("*", Collections.emptyList());
  }


  @Test
  public void test2() {
    final TFact.Tester t = factory.make(TFact.Tester.notEmptyTokens + TFact.Tester.lastIsNumber + TFact.Tester.concatsTokens);
    t.test("+", asList("1"), asList("1", "+"));
    t.test("-", asList("2"), asList("2", "-"));
    t.test("*", asList("3.14"), asList("3.14", "*"));
    t.test("/", asList("42"), asList("42", "/"));
  }


  @Test
  public void test3() {
    final TFact.Tester t = factory.make(TFact.Tester.notEmptyTokens + TFact.Tester.lastIsOperation + TFact.Tester.notChanged);
    t.testNotChanged("+", asList("1", "+"));
    t.testNotChanged("+", asList("42", "*"));
  }
}
