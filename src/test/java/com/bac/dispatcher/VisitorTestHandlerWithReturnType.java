package com.bac.dispatcher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Tests the handler method for a Visitor. The test class is a wrapper which
 * routes method calls to a mock object which can be verified by Mockito.
 * Currently there is no way to identify the method signature in a
 * Mockito.verify() call so the signature is clarified by this structure
 * 
 * @author Simon Baird
 *
 */

public class VisitorTestHandlerWithReturnType extends AbstractVisitorTest {

	@Mock
	private MockDestination instance;

	private DispatchHandler<?> dispatcher;

	private HandlerVisitor visitor;
	
	private final Long STRING_HANDLER_RETURN = 11L;
	private final Long INTEGER_HANDLER_RETURN = 21L;
	private final Long INTERFACE_HANDLER_RETURN = 31L;
	private final Long SUPERCLASS_HANDLER_RETURN = 41L;
	private final Long SUBCLASS_HANDLER_RETURN = 51L;

	@Before
	public void setEnv() {

		dispatcher = new DispatchHandler<Long>();
		visitor = new HandlerVisitor(instance);
	}

	/**
	 * A Boolean object has no overridden method in the test Visitor so calls
	 * using this target should be routed to the default method which means none
	 * of the mock methods should have been invoked
	 */
	@Test
	public void boolean_Target_Should_Call_Default_Handle() {

		Object targetObject = false;
		assertNull(dispatcher.handle(visitor, targetObject));

		verify(instance, never()).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance, never()).subClassHandle(any());
	}
	
	/**
	 * A String target object has a specific method handler which should be the
	 * only method invoked
	 */
	@Test
	public void string_Target_Should_Call_String_Handler() {

		Object targetObject = "string";
		assertEquals(STRING_HANDLER_RETURN, dispatcher.handle(visitor, targetObject));
		
		verify(instance).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance, never()).subClassHandle(any());
	}

	/**
	 * An Integer target object has a specific method handler which should be
	 * the only method invoked
	 */
	@Test
	public void integer_Target_Should_Call_Integer_Handler() {

		Object targetObject = new Integer(1);
		assertEquals(INTEGER_HANDLER_RETURN, dispatcher.handle(visitor, targetObject));
		
		verify(instance, never()).stringHandle(any());
		verify(instance).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance, never()).subClassHandle(any());
	}

	/**
	 * A supported interface target should invoke the appropriate interface
	 * method as the only method invoked
	 */
	@Test
	public void interface_Target_Should_Call_Interface_Handler() {

		Object targetObject = new MethodIdentifier() {
		};
		assertEquals(INTERFACE_HANDLER_RETURN, dispatcher.handle(visitor, targetObject));
		
		verify(instance, never()).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance, never()).subClassHandle(any());
	}

	/**
	 * An unsupported interface has no overridden method in the test Visitor so
	 * calls using this target should be routed to the default method which
	 * means none of the mock methods should have been invoked
	 */
	@Test
	public void unkown_Interface_Target_Should_Call_Default_Handler() {

		Object targetObject = new UnsupportedInterface() {
		};
		assertNull(dispatcher.handle(visitor, targetObject));

		verify(instance, never()).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance, never()).subClassHandle(any());
	}
	
	/**
	 * Class hierarchies should be supported so that only the appropriate method
	 * should have been invoked
	 */
	@Test
	public void superClass_Target_Should_Call_SuperClass_Handler() {

		Object targetObject = new SuperClass();
		assertEquals(SUPERCLASS_HANDLER_RETURN, dispatcher.handle(visitor, targetObject));

		verify(instance, never()).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance).superClassHandle(any());
		verify(instance, never()).subClassHandle(any());
	}
	
	/**
	 * Class hierarchies should be supported so that only the appropriate method
	 * should have been invoked
	 */
	@Test
	public void subClass_Target_Should_Call_SubClass_Handler() {

		Object targetObject = new SubClass();
		assertEquals(SUBCLASS_HANDLER_RETURN, dispatcher.handle(visitor, targetObject));

		verify(instance, never()).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance).subClassHandle(any());
	}

	/**
	 * A member of a class hierarchy that is not directly supported should
	 * default to the narrowest available definition. The FinalClass invocation
	 * should call its direct ancestor, SubClass
	 */
	@Test
	public void finalClass_Target_Should_Call_SubClass_Handler() {

		Object targetObject = new FinalClass();
		assertEquals(SUBCLASS_HANDLER_RETURN, dispatcher.handle(visitor, targetObject));

		verify(instance, never()).stringHandle(any());
		verify(instance, never()).integerHandle(any());
		verify(instance, never()).interfaceHandle(any());
		verify(instance, never()).superClassHandle(any());
		verify(instance).subClassHandle(any());
	}
	
	@Test(expected=ClassCastException.class)
	public void calling_Visitor_With_Incorrect_Return_Type_Should_Throw_Exception() {
		
		final DispatchHandler<String> stringDispatcher = new DispatchHandler<String>();
		@SuppressWarnings("unused")
		final String result = stringDispatcher.handle(visitor, "string");
	}
	
	
	/**
	 * Test Visitor class. This will forward calls to the mock destination and
	 * allow method invocation to be verified. There is no way using
	 * verify().someMethod() determine the someMethod()'s signature.
	 * 
	 */
	class HandlerVisitor implements Visitor<Long> {

		private final MockDestination mock;

		public HandlerVisitor(MockDestination mock) {

			this.mock = mock;
		}

		public Long handle(String o) {
			mock.stringHandle(o);
			return STRING_HANDLER_RETURN;
		}

		public Long handle(Integer o) {

			mock.integerHandle(o);
			return INTEGER_HANDLER_RETURN;
		}

		public Long handle(MethodIdentifier o) {
			mock.interfaceHandle(o);
			return INTERFACE_HANDLER_RETURN;
		}

		public Long handle(SuperClass o) {
			mock.superClassHandle(o);
			return SUPERCLASS_HANDLER_RETURN;
		}

		public Long handle(SubClass o) {
			mock.subClassHandle(o);
			return SUBCLASS_HANDLER_RETURN;
		}
	}

	/**
	 * Destination Interface to mock. Methods have different names and the Test
	 * Visitor will redirect calls to this. This will allow for Mockito.verify
	 * to evaluate the call
	 *
	 */
	interface MockDestination {

		void stringHandle(String o);

		void integerHandle(Integer o);

		void interfaceHandle(MethodIdentifier o);

		void superClassHandle(SuperClass o);

		void subClassHandle(SubClass o);

	}
}
