package backend.Backend__tests;


import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.example.prime.ascalc.Backend;

import static java.util.Arrays.asList;


public class Backend_addDot_Tests {
  private final Function<String, BiConsumer<List<String>, List<String>>> sut =
      TFact.tester.apply(Backend::addDot);


  @Test
  public void test1() {
    final BiConsumer<List<String>, List<String>> test = sut.apply("should add dot");
    test.accept(Collections.emptyList(), asList("0."));
    test.accept(asList("0"), asList("0."));
    test.accept(asList("1", "+"), asList("1", "+", "0."));
  }


  @Test
  public void test2() {
    final BiConsumer<List<String>, List<String>> test = sut.apply("should not add dot");
    test.accept(asList("0."), asList("0."));
    test.accept(asList("0.1"), asList("0.1"));
  }
}
