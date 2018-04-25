package com.calliduslynx.checky;


// TODO evaluate if its better to use BigDecimal instead of int
// TODO complete all missing unit tests

import com.calliduslynx.checky.pricingrules.NewPriceForNItems;
import com.calliduslynx.checky.pricingrules.PercentDiscountWhenEnoughTotal;

/**
 * General:
 * - All prices are in euro cent. So a value of 150 means 1.50€
 * - If possible all fields are immutable
 */
public class Main {
  public static void main(String[] args) {
    CheckOut co = new CheckoutConfigurator()
        .withArticles(
            new Article("A", 150),
            new Article("B", 200)
        ).withPricingRules(
            new NewPriceForNItems("PR1", "A", 3, 400),
            new PercentDiscountWhenEnoughTotal("WSV", 300, 20)
        ).configure();

    co.scan(2, "A");
    co.scan("B");
    co.scan("A");
    co.scan("B");

    System.out.println(co);
  }
}
