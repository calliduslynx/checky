package com.calliduslynx.checky;

import org.junit.Test;

public class SimpleArticleTest extends CheckyTest {
  @Test
  public void SimpleArticle_can_be_instanciated() {
    new Article("A", 100);
    new Article("ABC-DEF", 102);
  }

  @Test(expected = IllegalArgumentException.class)
  public void instantiation_fails_if_skuId_is_invalid__1() {
    new Article("", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void instantiation_fails_if_skuId_is_invalid__2() {
    new Article(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void instantiation_fails_if_skuId_is_invalid__3() {
    new Article("    ", 1);
  }
}