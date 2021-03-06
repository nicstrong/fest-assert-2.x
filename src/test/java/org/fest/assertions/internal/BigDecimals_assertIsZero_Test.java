/*
 * Created on Feb 8, 2011
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static java.math.BigDecimal.*;
import static org.fest.assertions.test.TestData.someInfo;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.fest.assertions.core.AssertionInfo;
import org.junit.*;

/**
 * Tests for <code>{@link BigDecimals#assertIsZero(AssertionInfo, BigDecimal)}</code>.
 *
 * @author Yvonne Wang
 */
public class BigDecimals_assertIsZero_Test {

  private Comparables comparables;
  private BigDecimals bigDecimals;

  @Before public void setUp() {
    comparables = mock(Comparables.class);
    bigDecimals = new BigDecimals();
    bigDecimals.comparables = comparables;
  }

  @Test public void should_verify_that_actual_is_equal_to_zero() {
    AssertionInfo info = someInfo();
    bigDecimals.assertIsZero(info, ONE);
    verify(comparables).assertEqualByComparison(info, ONE, ZERO);
  }
}
