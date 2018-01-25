package backend.Backend__tests;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.example.prime.ascalc.Backend;

import static java.util.Arrays.asList;


public class Backend_flipSign_Tests {
  private final Function<String, BiConsumer<List<String>, List<String>>> sut =
      TFact.tester.apply(Backend::flipSign);


  @Test
  public void test1() {
    final BiConsumer<List<String>, List<String>> test = sut.apply("should work");

    test.accept(asList("-1"), asList("1"));
    test.accept(asList("1"), asList("-1"));
    test.accept(asList("0.1"), asList("-0.1"));

    test.accept(Collections.emptyList(), Collections.emptyList());
    test.accept(asList("0"), asList("0"));
    test.accept(asList("0."), asList("0."));
    test.accept(asList("1", "+"), asList("1", "+"));
  }
}

