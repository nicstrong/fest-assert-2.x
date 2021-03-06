/*
 * Created on Nov 29, 2010
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
package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;
import static org.fest.assertions.test.ObjectArrayFactory.emptyArray;
import static org.fest.assertions.test.TestData.someIndex;
import static org.mockito.Mockito.*;

import org.fest.assertions.data.Index;
import org.fest.assertions.internal.ObjectArrays;
import org.junit.*;

/**
 * Tests for <code>{@link ObjectArrayAssert#contains(Object, Index)}</code>.
 *
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_contains_at_Index_Test {

  private ObjectArrays arrays;
  private ObjectArrayAssert assertions;

  @Before public void setUp() {
    arrays = mock(ObjectArrays.class);
    assertions = new ObjectArrayAssert(emptyArray());
    assertions.arrays = arrays;
  }

  @Test public void should_verify_that_actual_contains_value_at_index() {
    Index index = someIndex();
    assertions.contains("Yoda", index);
    verify(arrays).assertContains(assertions.info, assertions.actual, "Yoda", index);
  }

  @Test public void should_return_this() {
    ObjectArrayAssert returned = assertions.contains("Luke", someIndex());
    assertSame(assertions, returned);
  }
}
