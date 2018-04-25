package com.calliduslynx.checky.pricingrules;

import org.junit.Test;
import com.calliduslynx.checky.Article;
import com.calliduslynx.checky.CheckOut;
import com.calliduslynx.checky.CheckoutConfigurator;
import com.calliduslynx.checky.CheckyTest;

public class NewPriceForNItemsMultipleTest extends CheckyTest {
  private CheckOut co1 = new CheckoutConfigurator()
      .withArticles(new Article("A", 100))
      .withPricingRules(
          new NewPriceForNItems("PR1", "A", 5, 400),
          new NewPriceForNItems("PR2", "A", 3, 250)
      )
      .configure();

  @Test
  public void rules_are_applied_by_order__1() {
    co1.scan(5, "A");
    assertTotal(400, co1);
  }

  @Test
  public void rules_are_applied_by_order__2() {
    co1.scan(3, "A");
    assertTotal(250, co1);
  }

  @Test
  public void rules_are_applied_by_order__3() {
    co1.scan(9, "A");
    assertTotal(750, co1); // 400 + 250 + 100
  }
}