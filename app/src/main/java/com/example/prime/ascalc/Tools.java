package com.example.prime.ascalc;

import java.util.ArrayList;
import java.util.List;


public abstract class Tools {
  static String last(List<String> ts) {
    return ts.get(ts.size() - 1);
  }


  static List<String> replaceLast(List<String> xs, String x) {
    return concat(xs.subList(0, xs.size() - 1), x);
  }


  static List<String> removeLast(List<String> tokens) {
    return tokens.subList(0, tokens.size() - 1);
  }


  static <T> List<T> concat(List<T> xs, T x) {
    final List<T> temp = new ArrayList<>();
    temp.add(x);

    return concat(xs, temp);
  }


  static <T> List<T> concat(T x, List<T> xs) {
    final List<T> temp = new ArrayList<>();
    temp.add(x);

    return concat(temp, xs);
  }


  @SafeVarargs
  private static <T> List<T> concat(List<T>... args) {
    List<T> ret = new ArrayList<>();
    for (List<T> list : args)
      ret.addAll(list);

    return ret;
  }
}
