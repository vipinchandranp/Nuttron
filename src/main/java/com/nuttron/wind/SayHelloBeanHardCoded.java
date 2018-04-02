package com.nuttron.wind;

import java.lang.reflect.Method;

public class SayHelloBeanHardCoded {

	private static final String HELLO_MSG = "Hello ";

	@PersonneName(name = "World !! (simple annotation)")
	public String sayHelloTo(String name) {
		return HELLO_MSG + name;
	}

	public static void main(String[] args) {

		try {
			// instanciate the bean
			SayHelloBeanHardCoded simpleBean = new SayHelloBeanHardCoded();
			// get the method descriptor through reflection
			Method helloMessageMethod = simpleBean.getClass().getDeclaredMethod("sayHelloTo", String.class);
			// scan the annotation
			PersonneName mySimpleAnnotation = (PersonneName) helloMessageMethod.getAnnotation(PersonneName.class);

			System.out.println(simpleBean.sayHelloTo(mySimpleAnnotation.name()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}