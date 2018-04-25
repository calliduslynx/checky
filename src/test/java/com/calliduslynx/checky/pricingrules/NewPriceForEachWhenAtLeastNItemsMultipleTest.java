package com.calliduslynx.checky.pricingrules;

import org.junit.Test;
import com.calliduslynx.checky.Article;
import com.calliduslynx.checky.CheckOut;
import com.calliduslynx.checky.CheckoutConfigurator;
import com.calliduslynx.checky.CheckyTest;

public class NewPriceForEachWhenAtLeastNItemsMultipleTest extends CheckyTest {
  private CheckOut co1 = new CheckoutConfigurator()
      .withArticles(new Article("A", 100))
      .withPricingRules(
          new NewPriceForEachWhenAtLeastNItems("PR1", "A", 100, 50), // for 100+ items -> 0,50€ each 
          new NewPriceForEachWhenAtLeastNItems("PR1", "A", 10, 70),  // for  10+ items -> 0,70€ each
          new NewPriceForEachWhenAtLeastNItems("PR1", "A", 3, 80)    // for   3+ items -> 0,80€ each
      )
      .configure();

  @Test
  public void rules_are_ordered__1() {
    co1.scan(2, "A");

    assertTotal(200, co1); // 2 x 100
  }

  @Test
  public void rules_are_ordered__2() {
    co1.scan(3, "A");

    assertTotal(240, co1); // 3 x 80
  }

  @Test
  public void rules_are_ordered__3() {
    co1.scan(9, "A");

    assertTotal(720, co1); // 9 x 80
  }

  @Test
  public void rules_are_ordered__4() {
    co1.scan(10, "A");

    assertTotal(700, co1); // 10 x 70
  }

  @Test
  public void rules_are_ordered__5() {
    co1.scan(99, "A");

    assertTotal(6930, co1); // 99 x 70
  }

  @Test
  public void rules_are_ordered__7() {
    co1.scan(100, "A");

    assertTotal(5000, co1); // 100 x 50
  }

  @Test
  public void rules_are_ordered__8() {
    co1.scan(125, "A");

    assertTotal(6250, co1); // 125 x 50
  }
}