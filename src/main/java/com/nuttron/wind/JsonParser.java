package com.nuttron.wind;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.nuttron.wind.util.PojoGenerator;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class JsonParser {

	public static void main(String[] args) throws IOException, SecurityException, CannotCompileException,
			RuntimeException, IllegalAccessException, InstantiationException, ClassNotFoundException, NotFoundException,
			InvocationTargetException, NoSuchMethodException {
		Map<String, Class<?>> props = new HashMap<String, Class<?>>();
		props.put("aId", Integer.class);
		props.put("aValue", String.class);

		Class<?> clazz = PojoGenerator.generate("com.nuttron.wind.generated.Entity$Generated", props);

		Object obj = clazz.newInstance();

		System.out.println("Clazz: " + clazz);
		System.out.println("Object: " + obj);
		System.out.println("Serializable? " + (obj instanceof Serializable));

		for (final Method method : clazz.getDeclaredMethods()) {
			System.out.println(method);
		}

		// set property "bar"
		clazz.getMethod("setBar", String.class).invoke(obj, "Hello World!");

		// get property "bar"
		String result = (String) clazz.getMethod("getBar").invoke(obj);
		System.out.println("Value for bar: " + result);
	}
}
