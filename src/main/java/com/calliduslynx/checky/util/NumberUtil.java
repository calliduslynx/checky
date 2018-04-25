package com.calliduslynx.checky.util;

public class NumberUtil {
  public static final String CURRENCY = "€";

  /** Converts int to nice looking Euro ( 1123 -> "11,23€" ) */
  public static String asEuro(int value) {
    return String.format("%s%d,%02d%s",
        value < 0 ? "-" : "",
        Math.abs(value / 100),
        Math.abs(value % 100),
        CURRENCY);
  }
}
