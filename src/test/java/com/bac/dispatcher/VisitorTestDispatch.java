package com.bac.dispatcher;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Tests the dispatch method for a Visitor. The test class is a wrapper which
 * routes method calls to a mock object which can be verified by Mockito.
 * Currently there is no way to identify the method signature in a
 * Mockito.verify() call so the signature is clarified by this structure
 * 
 * @author Simon Baird
 *
 */

public class VisitorTestDispatch extends AbstractVisitorTest {

	@Mock MockDestination instance;

	DispatchVisitor visitor;
	@Before
	public void setEnv() {
	
		dispatcher = new DefaultDispatchHandler();
		visitor = new DispatchVisitor(instance);
	}

	/**
	 * A Boolean object has no overridden method in the test Visitor so calls
	 * using this target should be routed to the default method which means none
	 * of the mock methods should have been invoked
	 */
	@Test
	public void boolean_Target_Should_Call_Default_Dispatch() {

		Object targetObject = false;
		dispatcher.dispatch(visitor, targetObject);

		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance, never()).subClassDispatch(any());
	}

	/**
	 * A String target object has a specific method handler which should be the
	 * only method invoked
	 */
	@Test
	public void string_Target_Should_Call_String_Dispatch() {

		Object targetObject = "string";
		dispatcher.dispatch(visitor, targetObject);

		verify(instance).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance, never()).subClassDispatch(any());
	}

	/**
	 * An Integer target object has a specific method handler which should be
	 * the only method invoked
	 */
	@Test
	public void integer_Target_Should_Call_Integer_Dispatch() {

		Object targetObject = new Integer(1);
		dispatcher.dispatch(visitor, targetObject);

		verify(instance).integerDispatch(any());
		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance, never()).subClassDispatch(any());
	}

	/**
	 * A supported interface target should invoke the appropriate interface
	 * method as the only method invoked
	 */
	@Test
	public void interface_Target_Should_Call_Interface_Dispatch() {

		Object targetObject = new MethodIdentifier() {
		};
		dispatcher.dispatch(visitor, targetObject);

		verify(instance).interfaceDispatch(any());
		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance, never()).subClassDispatch(any());
	}

	/**
	 * An unsupported interface has no overridden method in the test Visitor so
	 * calls using this target should be routed to the default method which
	 * means none of the mock methods should have been invoked
	 */
	@Test
	public void unkown_Interface_Target_Should_Call_Default_Dispatch() {

		Object targetObject = new UnsupportedInterface() {
		};
		dispatcher.dispatch(visitor, targetObject);

		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance, never()).subClassDispatch(any());
	}

	/**
	 * Class hierarchies should be supported so that only the appropriate method
	 * should have been invoked
	 */
	@Test
	public void superClass_Target_Should_Call_SuperClass_Dispatch() {

		Object targetObject = new SuperClass();
		dispatcher.dispatch(visitor, targetObject);

		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance).superClassDispatch(any());
		verify(instance, never()).subClassDispatch(any());
	}

	/**
	 * Class hierarchies should be supported so that only the appropriate method
	 * should have been invoked
	 */
	@Test
	public void subClass_Target_Should_Call_SubClass_Dispatch() {

		Object targetObject = new SubClass();
		dispatcher.dispatch(visitor, targetObject);

		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance).subClassDispatch(any());
	}

	/**
	 * A member of a class hierarchy that is not directly supported should
	 * default to the narrowest available definition. The FinalClass invocation
	 * should call its direct ancestor
	 */
	@Test
	public void finalClass_Target_Should_Call_SubClass_Dispatch() {

		Object targetObject = new FinalClass();
		dispatcher.dispatch(visitor, targetObject);

		verify(instance, never()).stringDispatch(any());
		verify(instance, never()).integerDispatch(any());
		verify(instance, never()).interfaceDispatch(any());
		verify(instance, never()).superClassDispatch(any());
		verify(instance).subClassDispatch(any());
	}

	/**
	 * Test Visitor class. This will forward calls to the mock destination and
	 * allow method invocation to be verified. There is no way using
	 * verify().someMethod() determine the someMethod()'s signature.
	 * 
	 */
	class DispatchVisitor implements Visitor {

		private final MockDestination mock;

		public DispatchVisitor(MockDestination mock) {

			this.mock = mock;
		}

		public void dispatch(String o) {
			mock.stringDispatch(o);
		}

		public void dispatch(Integer o) {
			mock.integerDispatch(o);
		}

		public void dispatch(MethodIdentifier o) {
			mock.interfaceDispatch(o);
		}

		public void dispatch(SuperClass o) {
			mock.superClassDispatch(o);
		}

		public void dispatch(SubClass o) {
			mock.subClassDispatch(o);
		}
	}
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


}
