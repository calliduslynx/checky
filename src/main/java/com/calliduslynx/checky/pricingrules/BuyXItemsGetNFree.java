package com.calliduslynx.checky.pricingrules;

import com.calliduslynx.checky.Article;
import com.calliduslynx.checky.PricingRule;
import com.calliduslynx.checky.ShoppingCart;
import com.calliduslynx.checky.bill.Bill;

/**
 * With this rule you can specify that if you buy x items of an article
 * then you get n for free
 */
public class BuyXItemsGetNFree extends PricingRule {
  private final String skuId;
  private final int itemsToBuy;
  private final int itemsToGetFree;

  /**
   * @param name           name of the discount action
   * @param skuId          skuId of article for which action should work
   * @param itemsToBuy     how many items you need to buy to get free ones
   * @param itemsToGetFree how many items you get for free
   */
  public BuyXItemsGetNFree(String name, String skuId, int itemsToBuy, int itemsToGetFree) {
    super(name);
    this.skuId = skuId;
    this.itemsToBuy = itemsToBuy;
    this.itemsToGetFree = itemsToGetFree;
  }

  @Override
  protected void beforePosititionsAdded(ShoppingCart sc, Bill bill) {
    int count = sc.getCount(skuId);
    int blocks = count / itemsToBuy;
    if (blocks == 0)
      return;
    Article article = sc.getArticle(skuId);

    int itemsToPayFor = count - blocks;

    sc.reduce(skuId, count); // removing all from shopping card

    // add the items to pay with corrent price on the bill
    bill.addPosition(skuId, itemsToPayFor, article.getPricePerUnit(), itemsToPayFor * article.getPricePerUnit());
    // add the free ones
    bill.addPosition(skuId, blocks, 0, 0)
        .withComment("Since you bought " + count + " items " + blocks + " " + (blocks == 1 ? "is" : "are") + " for free " + getIdentifier());
  }
}
