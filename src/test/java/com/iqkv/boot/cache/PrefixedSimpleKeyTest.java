package com.iqkv.boot.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PrefixedSimpleKeyTest {

  @Test
  void sameMethodSamePrefixSameParametersShouldBeSame() {
    PrefixedSimpleKey prefixedSimpleKey = new PrefixedSimpleKey("prefix", "setX", "x", "y");
    PrefixedSimpleKey prefixedSimpleKey2 = new PrefixedSimpleKey("prefix", "setX", "x", "y");

    Assertions.assertThat(prefixedSimpleKey).isEqualTo(prefixedSimpleKey2);
    Assertions.assertThat(prefixedSimpleKey.hashCode()).isEqualTo(prefixedSimpleKey2.hashCode());
  }

  @Test
  void sameMethodSamePrefixSameParameterShouldBeSame() {
    PrefixedSimpleKey prefixedSimpleKey = new PrefixedSimpleKey("prefix", "setX", "x");
    PrefixedSimpleKey prefixedSimpleKey2 = new PrefixedSimpleKey("prefix", "setX", "x");

    Assertions.assertThat(prefixedSimpleKey).isEqualTo(prefixedSimpleKey2);
    Assertions.assertThat(prefixedSimpleKey.hashCode()).isEqualTo(prefixedSimpleKey2.hashCode());
  }

  @Test
  void sameMethodDifferentPrefixShouldBeDifferent() {
    PrefixedSimpleKey prefixedSimpleKey = new PrefixedSimpleKey("prefix", "setX", "x");
    PrefixedSimpleKey prefixedSimpleKey2 = new PrefixedSimpleKey("prefix2", "setX", "x");

    Assertions.assertThat(prefixedSimpleKey).isNotEqualTo(prefixedSimpleKey2);
    Assertions.assertThat(prefixedSimpleKey.hashCode()).isNotEqualTo(prefixedSimpleKey2.hashCode());
  }

  @Test
  void sameMethodDifferentParametersShouldBeDifferent() {
    PrefixedSimpleKey prefixedSimpleKey = new PrefixedSimpleKey("prefix", "setX", "x");
    PrefixedSimpleKey prefixedSimpleKey2 = new PrefixedSimpleKey("prefix", "setX", "y");

    Assertions.assertThat(prefixedSimpleKey).isNotEqualTo(prefixedSimpleKey2);
    Assertions.assertThat(prefixedSimpleKey.hashCode()).isNotEqualTo(prefixedSimpleKey2.hashCode());
  }
}
