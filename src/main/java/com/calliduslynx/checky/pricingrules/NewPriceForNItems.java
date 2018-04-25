package com.calliduslynx.checky.pricingrules;

import com.calliduslynx.checky.PricingRule;
import com.calliduslynx.checky.ShoppingCart;
import com.calliduslynx.checky.bill.Bill;

/**
 * With this rule you can create item bundles. If for example one A costs 1€ you could sell
 * 5 As for 4€.
 */
public class NewPriceForNItems extends PricingRule {
  private final String skuId;
  private final int countForNewPrice;
  private final int priceForAllTogether;


  public NewPriceForNItems(String name, String skuId, int countForNewPrice, int priceForAllTogether) {
    super(name);
    this.skuId = skuId;
    this.countForNewPrice = countForNewPrice;
    this.priceForAllTogether = priceForAllTogether;
  }

  @Override
  protected void beforePosititionsAdded(ShoppingCart sc, Bill bill) {
    int count = sc.getCount(skuId);
    int blocks = count / countForNewPrice;
    if (blocks == 0)
      return;

    int totalUnits = blocks * countForNewPrice;
    sc.reduce(skuId, totalUnits);

    bill.addPosition(skuId, totalUnits, null, priceForAllTogether * blocks)
        .withComment("Special price for " + countForNewPrice + " items " + getIdentifier());
  }
}
