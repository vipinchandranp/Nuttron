package com.nuttron.wind;

public class SayHelloBean {

	private static final String HELLO_MSG = "Hello ";

	public String sayHelloTo(String name) {
		return HELLO_MSG + name;
	}
}
