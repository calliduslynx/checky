package com.calliduslynx.checky;

public class ArticleNotFoundException extends RuntimeException {
  public ArticleNotFoundException(String skuIdOfNotFoundArticle) {
    super("The article with sku id '" + skuIdOfNotFoundArticle + "' was not found.");
  }
}
