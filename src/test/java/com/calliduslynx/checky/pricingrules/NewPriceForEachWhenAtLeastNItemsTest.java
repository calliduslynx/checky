package com.calliduslynx.checky.pricingrules;

import org.junit.Test;
import com.calliduslynx.checky.Article;
import com.calliduslynx.checky.CheckOut;
import com.calliduslynx.checky.CheckoutConfigurator;
import com.calliduslynx.checky.CheckyTest;

public class NewPriceForEachWhenAtLeastNItemsTest extends CheckyTest {
  private CheckOut co1 = new CheckoutConfigurator()
      .withArticles(new Article("A", 100))
      .withPricingRules(new NewPriceForEachWhenAtLeastNItems("PR1", "A", 3, 80))
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

    assertTotal(240, co1); // 3 x 80 
  }

  @Test
  public void applies_if_total_is_larger_configured() {
    co1.scan(6, "A");

    assertTotal(480, co1);  // 6 x 80
  }
}