package com.calliduslynx.checky;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckOutTest extends CheckyTest {
  private CheckOut co1 = new CheckoutConfigurator()
      .withArticles(
          new Article("A", 100),
          new Article("B", 200),
          new Article("C", 300)
      ).configure();

  @Test
  public void simple_scanning_and_total_calculation_works() {
    co1.scan("A");
    co1.scan("B");
    co1.scan("C");
    co1.scan("A");
    co1.scan("B");

    assertTotal(900, co1);
  }

  @Test(expected = ArticleNotFoundException.class)
  public void throws_ArticleNotFoundException_if_article_cant_be_found() {
    co1.scan("D");
  }

  @Test
  public void toString_looks_nice_and_items_are_grouped() {
    co1.scan("A");
    co1.scan("B");
    co1.scan("C");
    co1.scan("A");
    co1.scan("B");
    co1.scan("A");

    String expected = "" +
        " A                3x   1,00€  :   3,00€\n" +
        " B                2x   2,00€  :   4,00€\n" +
        " C                1x   3,00€  :   3,00€\n" +
        "----------------------------------------\n" +
        "                        Total :  10,00€\n" +
        "\n";
    assertEquals(expected, co1.toString());
  }
}