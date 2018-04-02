package com.nuttron.wind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME) // The annotation is saved in the*.class and can be used by the JVM.
@Target(value = ElementType.METHOD) // The annotation can be used on methods.
public @interface PersonneName {
	public String name();
}