package com.calliduslynx.checky;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

/**
 * usage:
 * <pre>
 * {@code
 * CheckOut co = new CheckoutConfigurator()
 *    .withArticles(
 *        new Article("A", 150),
 *        new Article("B", 200)
 *    ).withPricingRules(
 *        new NewPriceForNItems("PR1", "A", 3, 400),
 *        new PercentDiscountWhenEnoughTotal("WSV", 300, 20)
 *    ).configure();
 * }
 * </pre>
 */
public final class CheckoutConfigurator {
  private final List<Article> articles = new ArrayList<>();
  private final List<PricingRule> pricingRules = new ArrayList<>();

  public CheckoutConfigurator withArticles(Article... articles) {
    this.articles.addAll(asList(articles));
    return this;
  }

  public CheckoutConfigurator withPricingRules(PricingRule... pricingRules) {
    this.pricingRules.addAll(asList(pricingRules));
    return this;
  }

  public CheckOut configure() {
    return new CheckOut(articles, pricingRules);
  }
}
