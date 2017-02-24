package com.bac.dispatcher;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractVisitorTest {

	protected DispatchHandler<?> dispatcher;

	/**
	 * Destination Interface to mock. Methods have different names and the Test
	 * Visitor will redirect calls to this. This will allow for Mockito.verify
	 * to evaluate the call
	 *
	 */
	protected interface MockDestination {

		void stringDispatch(String o);

		void integerDispatch(Integer o);

		void interfaceDispatch(MethodIdentifier o);

		void superClassDispatch(SuperClass o);

		void subClassDispatch(SubClass o);

	}

	/**
	 * A supported interface to identify object dispatch
	 */
	protected interface MethodIdentifier {
	}

	/**
	 * An interface to mark objects which has no implemented support in the test
	 * Vistor
	 *
	 * 
	 */
	protected interface UnsupportedInterface {

	}

	public AbstractVisitorTest() {
		super();
	}

	/**
	 * A simple class hierarchy to test method allocation
	 *
	 */
	class SuperClass {

	}

	/**
	 * A simple class hierarchy to test method allocation
	 *
	 */
	class SubClass extends SuperClass {

	}

	final class FinalClass extends SubClass {

	}

}