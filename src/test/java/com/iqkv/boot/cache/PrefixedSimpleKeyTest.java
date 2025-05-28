/*
 * Copyright 2025 IQKV Foundation Team, and the original author or authors from the JHipster project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
