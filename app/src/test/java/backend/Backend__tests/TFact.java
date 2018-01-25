package backend.Backend__tests;

import org.hamcrest.CoreMatchers;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertThat;


class TFact {

  static Function<
      Function<List<String>, List<String>>,
      Function<String, BiConsumer<List<String>, List<String>>>> tester =
      (sut) -> (reason) -> (tokens, expected) ->
          assertThat(reason, sut.apply(tokens), CoreMatchers.equalTo(expected));


  private final BiFunction<String, List<String>, List<String>> _sut;


  TFact(BiFunction<String, List<String>, List<String>> sut) {
    _sut = sut;
  }


  Tester make(String reason) {
    return new Tester(reason);
  }


  public class Tester {
    static final String lastTokenIs0 = "\nGiven last token is '0' number";
    static final String lastTokenIsNot0 = "\ngiven last token is not '0' number";
    static final String lastTokenIsNotNumber = "\ngiven last token is not a number";
    static final String lastIsNumber = "\ngiven last token is number";
    static final String lastIsOperation = "\ngiven last token is operation";

    static final String replacesLast = "\nreplaces last token";
    static final String addsToLast = "\nconcats last token";
    static final String concatsTokens = "\nconcats tokens list with given token";
    static final String notChanged = "\ntokens are not changed";

    static final String emptyTokens = "\nGiven empty tokens list";
    static final String notEmptyTokens = "\nGiven not empty tokens list";

    final private String _reason;


    Tester(String reason) {
      _reason = reason;
    }


    //todo: input analysis
    public void test(String token, List<String> tokens, List<String> expected) {
      assertThat(_reason, _sut.apply(token, tokens), CoreMatchers.equalTo(expected));
    }


    void testNotChanged(String token, List<String> tokens) {
      test(token, tokens, tokens);
    }
  }
}
