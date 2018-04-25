package com.calliduslynx.checky;

import java.util.Objects;

/**
 * simple pojo which repesents one article
 */
public class Article {
  private final String skuId;
  private final int pricePerUnit;

  public Article(String skuId, int price) {
    if (skuId == null || skuId.trim().isEmpty())
      throw new IllegalArgumentException("skuId is not valid");
    this.skuId = skuId;
    this.pricePerUnit = price;
  }

  // **************************** getter ***************************

  public String getSkuId() {
    return skuId;
  }

  public int getPricePerUnit() {
    return pricePerUnit;
  }

  // **************************** equals + hashcode based on skuId ***************************
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article that = (Article) o;
    return Objects.equals(skuId, that.skuId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skuId);
  }
}
