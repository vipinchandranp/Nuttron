package com.nuttron.wind;

import java.util.HashMap;
import java.util.Map;

import com.nuttron.wind.util.RuntimeAnnotations;

public class TestClass {

	public static void main(String[] args) {
		TestAnnotation annotation = TestClass.class.getAnnotation(TestAnnotation.class);
		System.out.println("TestClass annotation before:" + annotation);

		Map<String, Object> valuesMap = new HashMap<>();
		valuesMap.put("value", "some String");
		RuntimeAnnotations.putAnnotation(TestClass.class, TestAnnotation.class, valuesMap);

		annotation = TestClass.class.getAnnotation(TestAnnotation.class);
		System.out.println("TestClass annotation after:" + annotation);
	}
}