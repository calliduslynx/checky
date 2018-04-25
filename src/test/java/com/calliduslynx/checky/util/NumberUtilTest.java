package com.calliduslynx.checky.util;

import static com.calliduslynx.checky.util.NumberUtil.asEuro;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberUtilTest {

  @Test
  public void works() {
    assertEquals("0,00€", asEuro(0));
    assertEquals("0,01€", asEuro(1));
    assertEquals("0,10€", asEuro(10));
    assertEquals("1,00€", asEuro(100));
    assertEquals("12,34€", asEuro(1234));
    assertEquals("0,00€", asEuro(-0));
    assertEquals("-0,01€", asEuro(-1));
    assertEquals("-0,10€", asEuro(-10));
    assertEquals("-1,00€", asEuro(-100));
    assertEquals("-12,34€", asEuro(-1234));
  }


}