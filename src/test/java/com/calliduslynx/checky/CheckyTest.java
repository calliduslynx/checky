package com.calliduslynx.checky;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CheckyTest {

  protected void assertTotal(int expectedTotal, CheckOut co) {
    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    System.out.println(co);
    assertEquals(expectedTotal, co.getTotal());
  }

  protected void assertException(Runnable runnable, Class<? extends Exception> expectedExceptionType) {
    try {
      runnable.run();
      fail("Exception expected");
    } catch (Exception e) {
      assertEquals(expectedExceptionType, e.getClass());
    }
  }
}
