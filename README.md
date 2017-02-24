### Dispatch handler

####A simplification of the Visitor pattern for Java.

A common way of implementing the Visitor pattern in Java is with a double dispatch by implementing an interface that accepts a Visitor on the element to be extended. The element  then invokes a method on the Visitor passing itself as an argument which is directed to the appropriate overloaded method. This has two main drawbacks:

* The elements in the structure may be third party software (e.g. an implementation of the DOM model) and so will not have an interface that accepts a Visitor
* If the elements are third party and do implement an appropriate interface then they will need to be recompiled if the Visitor interface changes as Java links statically to overloaded methods.

These can be overcome by employing the dispatch-handler. When traversing the element structure calls to the Visitor automatically invoke the most appropriate method without any need to impose an interface on the element class. Neither does the Visitor interface need extending, just the implementing concrete Visitor instance to which overloaded visit() methods may be added.

```
Visitor v;
Object element;

DefaultDispatchHandler dh = new DefaultDispatchHandler();

dh.dispatch(v,element)			// if the visit() method has a void signature
```
or 

```
Object result = dh.handle(v,element)		// if an object is to be returned
```

If the result object is a narrower type then the dispatch handler can be instantiated with the expected return type from the Visitor. 
```
DispatchHandler<String> = new DispatchHandler<String>();
```

If an incorrect object type is return from the Visitor then a `ClassCastException` will be thrown.

___

Methods in the Visitor implementation may be added as required. Test examples are available in the test directory.

```
class VisitorImpl implements Visitor {

	public void dispatch(SomeClass o) {
	...
	}
	
	public void dispatch(SomeOtherClass o) {
	...
	}
	
	public Object handle(SomeClass o) {
	...
	return something;
	}

}

```