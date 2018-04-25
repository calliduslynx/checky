package com.calliduslynx.checky.pricingrules;

import static com.calliduslynx.checky.util.NumberUtil.asEuro;

import com.calliduslynx.checky.PricingRule;
import com.calliduslynx.checky.bill.Bill;

/**
 * With this rule you can configure a general discount if the total of the bill is high enough
 */
public class PercentDiscountWhenEnoughTotal extends PricingRule {
  private final int totalToReach;
  private final int percentDiscount;

  /**
   * @param name            name of the discount action
   * @param totalToReach    the total sum which must be reached to get discount
   * @param percentDiscount how much discount should customer get (25% -> 25)
   */
  public PercentDiscountWhenEnoughTotal(String name, int totalToReach, int percentDiscount) {
    super(name);
    this.totalToReach = totalToReach;
    this.percentDiscount = percentDiscount;
  }

  @Override
  protected  void afterTotalIsCalculated(Bill bill) {
    int total = bill.getTotal();
    if (total < totalToReach)
      return;
    int discount = total * percentDiscount / 100;

    bill.addPosition("" + percentDiscount + "% discount", 1, null, -discount)
        .withComment("on original price " + asEuro(total) + " " + getIdentifier());
    bill.calculateTotal(); // recalculate with new discount position
  }
}
