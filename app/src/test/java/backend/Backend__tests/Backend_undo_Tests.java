package backend.Backend__tests;


import org.junit.Test;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.example.prime.ascalc.Backend;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;


public class Backend_undo_Tests {
  private final Function<String, BiConsumer<List<String>, List<String>>> sut =
      TFact.tester.apply(Backend::undo);


  @Test
  public void test1() {
    final BiConsumer<List<String>, List<String>> test = sut.apply("should just work");

    test.accept(emptyList(), emptyList());
    test.accept(asList("1"), emptyList());
    test.accept(asList("1", "+"), asList("1"));
    test.accept(asList("1", "+", "1"), asList("1", "+"));

    test.accept(asList("11"), asList("1"));
    test.accept(asList("0.11"), asList("0.1"));
    test.accept(asList("0."), emptyList());

    test.accept(asList("-1"), emptyList());
  }
}
