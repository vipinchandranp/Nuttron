package com.nuttron.wind;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

public class AddRunTimeAnnotation {

	public static void addPersonneNameAnnotationToMethod(String className, String methodName) throws Exception {

		// pool creation
		ClassPool pool = ClassPool.getDefault();
		// extracting the class
		CtClass cc = pool.getCtClass(className);
		// looking for the method to apply the annotation on
		CtMethod sayHelloMethodDescriptor = cc.getDeclaredMethod(methodName);
		// create the annotation
		ClassFile ccFile = cc.getClassFile();
		ConstPool constpool = ccFile.getConstPool();
		AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
		Annotation annot = new Annotation("com.nuttron.wind.PersonneName", constpool);
		annot.addMemberValue("name", new StringMemberValue("World!! (dynamic annotation)", ccFile.getConstPool()));
		attr.addAnnotation(annot);
		// add the annotation to the method descriptor
		sayHelloMethodDescriptor.getMethodInfo().addAttribute(attr);

		// transform the ctClass to java class
		Class dynamiqueBeanClass = cc.toClass();
		// instanciating the updated class
		SayHelloBean sayHelloBean = (SayHelloBean) dynamiqueBeanClass.newInstance();

		try {

			Method helloMessageMethod = sayHelloBean.getClass().getDeclaredMethod(methodName, String.class);
			// getting the annotation
			PersonneName personneName = (PersonneName) helloMessageMethod.getAnnotation(PersonneName.class);
			System.out.println(sayHelloBean.sayHelloTo(personneName.name()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			AddRunTimeAnnotation.addPersonneNameAnnotationToMethod("com.nuttron.wind.SayHelloBean", "sayHelloTo");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}