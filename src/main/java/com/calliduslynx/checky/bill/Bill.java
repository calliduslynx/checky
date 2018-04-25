package com.calliduslynx.checky.bill;

import static com.calliduslynx.checky.util.NumberUtil.asEuro;

import java.util.ArrayList;
import java.util.List;

/**
 * The bill is a simple holding structure which contains all positions. It can calculate the total and
 * has a nice toString :)
 */
public class Bill {
  private List<BillPosition> positions = new ArrayList<>();

  private int total;

  /**
   * make sure to have {@link #calculateTotal()} called before getting total !!
   *
   * @return the total price of the bill
   */
  public int getTotal() {
    return total;
  }

  /**
   * adds one new position to the bill
   *
   * @param pricePerUnitOrNull can be null if irrelevant
   * @return the currently created position
   */
  public BillPosition addPosition(String articleName, int count, Integer pricePerUnitOrNull, int pricePosition) {
    BillPosition position = new BillPosition(articleName, count, pricePerUnitOrNull, pricePosition);
    positions.add(position);
    return position;
  }

  /** creates total by adding all prices of all positions together */
  public void calculateTotal() {
    total = positions.stream()
        .mapToInt(BillPosition::getPricePosition)
        .sum();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    positions.forEach(p ->
        sb.append(p.toString()).append("\n")
    );
    sb.append("----------------------------------------\n");
    sb.append(String.format("                        Total : %7s\n\n", asEuro(total)));
    return sb.toString();
  }
}


