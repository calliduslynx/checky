package com.calliduslynx.checky.pricingrules;

import org.junit.Test;
import com.calliduslynx.checky.Article;
import com.calliduslynx.checky.CheckOut;
import com.calliduslynx.checky.CheckoutConfigurator;
import com.calliduslynx.checky.CheckyTest;

public class BuyXItemsGetNFreeTest extends CheckyTest {
  private CheckOut co1 = new CheckoutConfigurator()
      .withArticles(new Article("A", 100))
      .withPricingRules(new BuyXItemsGetNFree("PR1", "A", 3, 1))
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
  public void applies_if_count_is_as_configured() {
    co1.scan(3, "A");

    assertTotal(200, co1);
  }

  @Test
  public void applies_several_times_if_count_is_as_configured() {
    co1.scan(6, "A");

    assertTotal(400, co1);
  }

  @Test
  public void applies_and_keeps_rest() {
    co1.scan(4, "A");

    assertTotal(300, co1); // 4 items means one for free
  }

  @Test
  public void applies_several_times_and_keeps_rest() {
    co1.scan(10, "A");

    assertTotal(700, co1); // 10 items -> 3 for free
  }
}