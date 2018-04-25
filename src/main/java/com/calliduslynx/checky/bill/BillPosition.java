package com.calliduslynx.checky.bill;

import static com.calliduslynx.checky.util.NumberUtil.asEuro;

/**
 * represents one line / position on the bill
 */
public class BillPosition {
  private final String description;
  private final int count;
  private final Integer pricePerUnitOrNull;
  private final int pricePosition;
  private String comment;

  BillPosition(String description, int count, Integer pricePerUnitOrNull, int pricePosition) {
    this.description = description;
    this.count = count;
    this.pricePerUnitOrNull = pricePerUnitOrNull;
    this.pricePosition = pricePosition;
  }

  int getPricePosition() {
    return pricePosition;
  }

  /** adds a comment to the current position */
  public void withComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return String.format(" %-14s %3dx %7s  : %7s%s",
        description, count, pricePerUnitOrNull == null ? "" : asEuro(pricePerUnitOrNull), asEuro(pricePosition),
        comment != null ? "\n   \\____" + comment : ""
    );
  }
}
