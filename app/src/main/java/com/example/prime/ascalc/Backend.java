package com.example.prime.ascalc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.example.prime.ascalc.Tools.*;


public abstract class Backend {
  public static String computeTokens(List<String> tokens) {
    String ret;

    if (tokens.size() < 3) {
      ret = tokens.get(0);
    } else {
      final String l = tokens.get(0);
      final String operation = tokens.get(1);
      final String r = tokens.get(2);

      final String result = Double.toString(operations()
          .get(operation)
          .apply(Double.parseDouble(l), Double.parseDouble(r)));

      if (tokens.size() > 3) {
        final List<String> rest = tokens.subList(3, tokens.size());
        ret = computeTokens(concat(result, rest));
      } else {
        ret = result;
      }
    }
    return ret;
  }


  public static List<String> addNumberToken(String token, List<String> tokens) {
    return tokens.isEmpty() || isOperationToken(last(tokens))
        ? concat(tokens, token)
        : Objects.equals(last(tokens), "0")
        ? replaceLast(tokens, token)
        : replaceLast(tokens, last(tokens) + token);
  }


  public static List<String> addOperationToken(String token, List<String> tokens) {
    return tokens.isEmpty() || isOperationToken(last(tokens))
        ? tokens
        : concat(tokens, token);
  }


  public static List<String> addDot(List<String> tokens) {
    return tokens.isEmpty()
        ? Collections.singletonList("0.")
        : isOperationToken(last(tokens))
        ? concat(tokens, "0.")
        : last(tokens).contains(".")
        ? tokens
        : replaceLast(tokens, last(tokens) + ".");
  }


  public static List<String> undo(List<String> tokens) {
    return tokens.isEmpty()
        ? tokens
        : isAtomaryToken(last(tokens))
        ? removeLast(tokens)
        : replaceLast(tokens, last(tokens).substring(0, last(tokens).length() - 1));
  }


  public static List<String> flipSign(List<String> tokens) {
    return tokens.isEmpty() || isInvalidNumber(last(tokens))
        ? tokens
        : last(tokens).startsWith("-")
        ? replaceLast(tokens, last(tokens).substring(1))
        : replaceLast(tokens, "-" + last(tokens));
  }


  private static HashMap<String, Fun> operations() {
    final HashMap<String, Fun> operations = new HashMap<>();
    operations.put("+", (l, r) -> l + r);
    operations.put("-", (l, r) -> l - r);
    operations.put("/", (l, r) -> l / r);
    operations.put("*", (l, r) -> l * r);

    return operations;
  }


  private interface Fun {
    Double apply(Double l, Double r);
  }


  private static boolean isInvalidNumber(String last) {
    return isOperationToken(last) || Objects.equals(last, "0") || Objects.equals(last, "0.");
  }


  private static boolean isAtomaryToken(String token) {
    return Objects.equals(token, "0.") ||
        isOperationToken(token) ||
        token.length() == 1 ||
        token.length() == 2 && Objects.equals(token.substring(0, 1), "-");
  }


  private static boolean isOperationToken(String token) {
    return operations().containsKey(token);
  }
}
