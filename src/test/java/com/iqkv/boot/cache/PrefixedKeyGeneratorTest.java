/*
 * Copyright 2025 Expertness Team, and the original author or authors from the JHipster project.
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;

class PrefixedKeyGeneratorTest {

  @Test
  void generatePrefixFromShortCommitId() {
    Properties gitProperties = new Properties();
    gitProperties.put("commit.id.abbrev", "1234");

    PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(new GitProperties(gitProperties), null);

    assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1234");
  }

  @Test
  void generatePrefixFromCommitId() {
    Properties gitProperties = new Properties();
    gitProperties.put("commit.id", "1234567");

    PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(new GitProperties(gitProperties), null);

    assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1234567");
  }

  @Test
  void generatePrefixFromBuildVersion() {
    Properties buildProperties = new Properties();
    buildProperties.put("version", "1.0.0");

    PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(null, new BuildProperties(buildProperties));

    assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1.0.0");
  }

  @Test
  void generatePrefixFromBuildTime() {
    Properties buildProperties = new Properties();
    buildProperties.put("time", "1583955265");

    PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(null, new BuildProperties(buildProperties));

    assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1970-01-19T07:59:15.265Z");
  }

  @Test
  void generatesRandomPrefix() {
    PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(null, null);

    assertThat(prefixedKeyGenerator.getPrefix().length()).isEqualTo(12);
  }
}
