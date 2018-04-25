package com.calliduslynx.checky;

import java.util.List;

import com.calliduslynx.checky.bill.Bill;

public class CheckOut {

  /** contains all articles which CheckOut instance knows */
  private final List<Article> articles;

  /** contains all pricing rules which CheckOut instance knows */
  private final List<PricingRule> pricingRules;

  private final ShoppingCart shoppingCart = new ShoppingCart();

  /** creation of bill is not so cheap so lets keep it cached */
  private Bill cachedBill;

  /**
   * it's recommended to use {@link CheckoutConfigurator} to create instances
   */
  CheckOut(List<Article> articles, List<PricingRule> pricingRules) {
    this.articles = articles;
    this.pricingRules = pricingRules;
  }

  /** adds one article with given skuId to shoppingCard */
  public void scan(String skuId) throws ArticleNotFoundException {
    scan(1, skuId);
  }

  /** adds <code>count</code> article with given skuId to shoppingCard */
  public synchronized void scan(int count, String skuId) throws ArticleNotFoundException {
    cachedBill = null; // invalidate cache
    Article article = getArticleForSkuId(skuId);
    shoppingCart.add(count, article);
  }

  public int getTotal() {
    return createBill().getTotal();
  }

  @Override
  public String toString() {
    return createBill().toString();
  }

  // ******************************************************************************************************************

  private Article getArticleForSkuId(String skuId) throws ArticleNotFoundException {
    return articles.stream()
        .filter(article -> article.getSkuId().equals(skuId))
        .findAny()
        .orElseThrow(() -> new ArticleNotFoundException(skuId));
  }

  private Bill createBill() {
    if (cachedBill != null)
      return cachedBill;

    // ***** create a new bill

    ShoppingCart sc = shoppingCart.getCopy(); // create a copy 'cause where removing items from shopping card .. better if it's not the original
    Bill bill = new Bill();

    // first apply beforePosititionsAdded actions
    pricingRules.forEach(pricingRule -> pricingRule.beforePosititionsAdded(sc, bill));

    // then add all articles with default price
    sc.forEach((article, count) -> bill.addPosition(
        article.getSkuId(),
        count,
        article.getPricePerUnit(),
        article.getPricePerUnit() * count
    ));

    // apply all afterPositionsAdded actions
    pricingRules.forEach(pricingRule -> pricingRule.afterPositionsAdded(bill));

    // calculate total
    bill.calculateTotal();

    // apply all afterTotalIsCalculated actions
    pricingRules.forEach(pricingRule -> pricingRule.afterTotalIsCalculated(bill));

    return bill;
  }
}
