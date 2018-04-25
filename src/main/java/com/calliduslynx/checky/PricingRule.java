package com.calliduslynx.checky;

import com.calliduslynx.checky.bill.Bill;

/**
 * The base class for all special pricing rules
 * <p>
 * Implementations should override at least one of
 * <ul>
 * <li>{@link #beforePosititionsAdded(ShoppingCart, Bill)}</li>
 * <li>{@link #afterPositionsAdded(Bill)}</li>
 * <li>{@link #afterTotalIsCalculated(Bill)} </li>
 * </ul>
 */
public abstract class PricingRule {
  private String name;

  /**
   * @param name name of the campain
   */
  protected PricingRule(String name) {
    this.name = name;
  }

  /**
   * @return an identifier which can be printed on the bill to identify the campain
   */
  protected String getIdentifier() {
    return "(#" + name + ")";
  }


  /**
   * this method is called before articles are moved from ShoppingCard to Bill
   * <p>
   * Implementations can remove items from ShoppingCard and create positions on the Bill
   */
  protected void beforePosititionsAdded(ShoppingCart sc, Bill bill) {
  }

  /**
   * this method is called after all positions are created on the bill but before total
   * price is calculated
   */
  protected void afterPositionsAdded(Bill bill) {
  }

  /**
   * this method is called after price was calculated
   */
  protected void afterTotalIsCalculated(Bill bill) {

  }
}
