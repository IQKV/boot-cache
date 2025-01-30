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

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * PrefixedSimpleKey class.
 */
public class PrefixedSimpleKey implements Serializable {

  private static final long serialVersionUID = 1L;
  private final String prefix;
  private transient Object[] params;
  private final String methodName;
  private int hashCodeValue;

  /**
   * Constructor for PrefixedSimpleKey.
   *
   * @param prefix     a {@link java.lang.String} object.
   * @param methodName a {@link java.lang.String} object.
   * @param elements   a {@link java.lang.Object} object.
   */
  public PrefixedSimpleKey(String prefix, String methodName, Object... elements) {
    Assert.notNull(prefix, "Prefix must not be null");
    Assert.notNull(elements, "Elements must not be null");
    this.prefix = prefix;
    this.methodName = methodName;
    params = new Object[elements.length];
    System.arraycopy(elements, 0, params, 0, elements.length);

    hashCodeValue = prefix.hashCode();
    hashCodeValue = 31 * hashCodeValue + methodName.hashCode();
    hashCodeValue = 31 * hashCodeValue + Arrays.deepHashCode(params);
  }

  @Override
  public boolean equals(Object other) {
    return (this == other
            || (other instanceof PrefixedSimpleKey
                && prefix.equals(((PrefixedSimpleKey) other).prefix)
                && methodName.equals(((PrefixedSimpleKey) other).methodName)
                && Arrays.deepEquals(params, ((PrefixedSimpleKey) other).params)));
  }

  @Override
  public final int hashCode() {
    return hashCodeValue;
  }


  @Override
  public String toString() {
    return prefix + " " + getClass().getSimpleName() + methodName + " [" + StringUtils.arrayToCommaDelimitedString(params) + "]";
  }
}
