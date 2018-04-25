package com.calliduslynx.checky;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * a simple holder class which contains all acticles and their counts
 * <p>
 * additionally it provides some helper methods to make it easier to use
 */
public class ShoppingCart {
  private final List<ShoppingCardPosition> positions = new ArrayList<>();

  public void add(int times, Article article) {
    ShoppingCardPosition positionOrNull = getPosition(article.getSkuId());
    if (positionOrNull == null) {
      positions.add(new ShoppingCardPosition(article, times));
    } else {
      positionOrNull.incCount(times);
    }
  }

  public void forEach(BiConsumer<Article, Integer> applyFunction) {
    positions.forEach(position ->
        applyFunction.accept(position.getArticle(), position.getCount())
    );
  }

  public ShoppingCart getCopy() {
    ShoppingCart newShoppingCard = new ShoppingCart();
    positions.forEach(position -> newShoppingCard.add(position.getCount(), position.getArticle()));
    return newShoppingCard;
  }

  public int getCount(String skuId) {
    ShoppingCardPosition positionOrNull = getPosition(skuId);
    return positionOrNull != null ? positionOrNull.getCount() : 0;
  }

  /** returns article or null */
  public Article getArticle(String skuId) {
    ShoppingCardPosition positionOrNull = getPosition(skuId);
    return positionOrNull != null ? positionOrNull.getArticle() : null;
  }

  /**
   * @throws ArticleNotFoundException if item wasn't found
   */
  public void reduce(String skuId, int times) {
    ShoppingCardPosition position = getPosition(skuId);
    if (position == null) throw new ArticleNotFoundException(skuId);

    position.decCount(times);

    // if count == 0 remove position completely
    if (position.getCount() == 0) positions.remove(position);
  }

  // ******************************************************************************************************************
  // ***** intern

  /** returns position or null */
  private ShoppingCardPosition getPosition(String skuId) {
    for (ShoppingCardPosition position : positions) {
      if (position.getArticle().getSkuId().equals(skuId))
        return position;
    }
    return null;
  }
}

class ShoppingCardPosition {
  private final Article article;
  private int count;

  ShoppingCardPosition(Article article, int count) {
    this.article = article;
    this.count = count;
  }

  Article getArticle() {
    return article;
  }

  int getCount() {
    return count;
  }

  void incCount(int delta) {
    count += delta;
  }

  void decCount(int delta) {
    count -= delta;
  }
}