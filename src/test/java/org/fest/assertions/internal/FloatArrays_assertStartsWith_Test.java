/*
 * Created on Dec 20, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.test.ErrorMessages.*;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.FloatArrayFactory.*;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link FloatArrays#assertStartsWith(AssertionInfo, float[], float[])}</code>.
 *
 * @author Alex Ruiz
 */
public class FloatArrays_assertStartsWith_Test {

  private static float[] actual;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private FloatArrays arrays;

  @BeforeClass public static void setUpOnce() {
    actual = array(6f, 8f, 10f, 12f);
  }

  @Before public void setUp() {
    failures = spy(new Failures());
    arrays = new FloatArrays();
    arrays.failures = failures;
  }

  @Test public void should_throw_error_if_sequence_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    arrays.assertStartsWith(someInfo(), actual, null);
  }

  @Test public void should_throw_error_if_sequence_is_empty() {
    thrown.expectIllegalArgumentException(valuesToLookForIsEmpty());
    arrays.assertStartsWith(someInfo(), actual, emptyArray());
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertStartsWith(someInfo(), null, array(6f));
  }

  @Test public void should_fail_if_sequence_is_bigger_than_actual() {
    float[] sequence = { 6f, 8f, 10f, 12f, 20f, 22f };
    try {
      AssertionInfo inof = someInfo();
      arrays.assertStartsWith(inof, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(sequence);
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test public void should_fail_if_actual_does_not_start_with_sequence() {
    AssertionInfo info = someInfo();
    float[] sequence = { 8f, 10f };
    try {
      arrays.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(sequence);
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test public void should_fail_if_actual_starts_with_first_elements_of_sequence_only() {
    AssertionInfo info = someInfo();
    float[] sequence = { 6f, 20f };
    try {
      arrays.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(sequence);
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  private void verifyFailureThrownWhenSequenceNotFound(float[] sequence) {
    verify(failures).failure(someInfo(), shouldStartWith(actual, sequence));
  }

  @Test public void should_pass_if_actual_starts_with_sequence() {
    arrays.assertStartsWith(someInfo(), actual, array(6f, 8f, 10f));
  }

  @Test public void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertStartsWith(someInfo(), actual, array(6f, 8f, 10f, 12f));
  }
}
