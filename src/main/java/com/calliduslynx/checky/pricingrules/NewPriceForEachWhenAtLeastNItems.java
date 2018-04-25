package com.calliduslynx.checky.pricingrules;

import com.calliduslynx.checky.PricingRule;
import com.calliduslynx.checky.ShoppingCart;
import com.calliduslynx.checky.bill.Bill;

/**
 * With this rule you could create low graduations (Preisstaffelung). If you buy enough items every item gets a new
 * price.
 */
public class NewPriceForEachWhenAtLeastNItems extends PricingRule {
  private final String skuId;
  private final int countForNewPrice;
  private final int newPriceForSingleItem;

  /**
   * @param name                  name of the discount action
   * @param skuId                 skuId of article for which action should work
   * @param countForNewPrice      how many items are at least to buy to get the new price
   * @param newPriceForSingleItem the new price for each item
   */
  public NewPriceForEachWhenAtLeastNItems(String name, String skuId, int countForNewPrice, int newPriceForSingleItem) {
    super(name);
    this.skuId = skuId;
    this.countForNewPrice = countForNewPrice;
    this.newPriceForSingleItem = newPriceForSingleItem;
  }

  @Override
  protected  void beforePosititionsAdded(ShoppingCart sc, Bill bill) {
    int count = sc.getCount(skuId);
    if (count < countForNewPrice)
      return;

    sc.reduce(skuId, count); // remove all

    bill.addPosition(skuId, count, newPriceForSingleItem, newPriceForSingleItem * count)
        .withComment("Special price for " + count + " items " + getIdentifier());
  }
}
