package com.bac.dispatcher;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	VisitorTestDispatch.class, 
	VisitorTestHandler.class, 
	VisitorTestHandlerWithReturnType.class 
})
public class AllTests {

}
