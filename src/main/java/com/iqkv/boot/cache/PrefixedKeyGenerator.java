/*
 * Copyright 2025 IQKV Team, and the original author or authors from the JHipster project.
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

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * PrefixedKeyGenerator class.
 *
 * <p>This class is responsible for generating cache keys that are specific to a version of the application
 * by prefixing them with git commit hash.
 *
 * <p>This allows multiple versions of an application to "share" the same distributed cache even when the structure
 * of the values has changed between those versions of the software.
 *
 * <p>This case typically occurs in production to ensure zero-downtime updates across a cluster
 * requiring that two different versions of the application have to run concurrently for some time.
 */
public class PrefixedKeyGenerator implements KeyGenerator {

  private final String prefix;
  private String shortCommitId = null;
  private Instant time = null;
  private String version = null;

  /**
   * Constructor for PrefixedKeyGenerator.
   *
   * @param gitProperties   a {@link org.springframework.boot.info.GitProperties} object.
   * @param buildProperties a {@link org.springframework.boot.info.BuildProperties} object.
   */
  public PrefixedKeyGenerator(GitProperties gitProperties, BuildProperties buildProperties) {
    prefix = generatePrefix(gitProperties, buildProperties);
  }

  String getPrefix() {
    return prefix;
  }

  private String generatePrefix(GitProperties gitProperties, BuildProperties buildProperties) {
    if (Objects.nonNull(gitProperties)) {
      shortCommitId = gitProperties.getShortCommitId();
    }

    if (Objects.nonNull(buildProperties)) {
      time = buildProperties.getTime();
      version = buildProperties.getVersion();
    }
    Object p = ObjectUtils.firstNonNull(shortCommitId, time, version, RandomStringUtils.randomAlphanumeric(12));

    if (p instanceof Instant) {
      return DateTimeFormatter.ISO_INSTANT.format((Instant) p);
    }
    return p.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object generate(Object target, Method method, Object... params) {
    return new PrefixedSimpleKey(prefix, method.getName(), params);
  }
}
