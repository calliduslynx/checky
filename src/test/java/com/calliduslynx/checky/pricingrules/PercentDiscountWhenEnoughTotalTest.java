package com.calliduslynx.checky.pricingrules;

import org.junit.Test;
import com.calliduslynx.checky.Article;
import com.calliduslynx.checky.CheckOut;
import com.calliduslynx.checky.CheckoutConfigurator;
import com.calliduslynx.checky.CheckyTest;

public class PercentDiscountWhenEnoughTotalTest extends CheckyTest {
  private CheckOut co1 = new CheckoutConfigurator()
      .withArticles(new Article("A", 100))
      .withPricingRules(new PercentDiscountWhenEnoughTotal("PR1", 300, 25))
      .configure();

  @Test
  public void no_failure_if_shoppingcart_is_empty() {
    assertTotal(0, co1);
  }

  @Test
  public void doesnt_apply_if_count_is_less() {
    co1.scan("A");
    co1.scan("A");
    assertTotal(200, co1);
  }

  @Test
  public void applies_if_total_is_as_configured() {
    co1.scan(3, "A");

    assertTotal(225, co1); // 75% of 300 
  }

  @Test
  public void applies_if_total_is_larger_configured() {
    co1.scan(6, "A");

    assertTotal(450, co1);  // 75% of 600
  }

  @Test
  public void applies_if_total_is_larger_configured__2() {
    co1.scan(10, "A");

    assertTotal(750, co1); // 75% of 1000 
  }
}