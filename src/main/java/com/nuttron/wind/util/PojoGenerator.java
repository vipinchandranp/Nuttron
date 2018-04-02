package com.nuttron.wind.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.EnumMemberValue;

public class PojoGenerator {

	public static Class generate(String className, Map<String, Class<?>> properties)
			throws NotFoundException, CannotCompileException {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass(className);

		// add this to define a super class to extend
		// cc.setSuperclass(resolveCtClass(MySuperClass.class));

		// add this to define an interface to implement
		cc.addInterface(resolveCtClass(Serializable.class));

		for (Entry<String, Class<?>> entry : properties.entrySet()) {

			cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));

			// add getter
			cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));

			// add setter
			cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
		}

		return cc.toClass();
	}

	public static Class generate(String className, Map<String, Class<?>> properties,
			HashMap<String, HashMap<Class<?>, HashMap<String, ?>>> annotationsMap)
			throws NotFoundException, CannotCompileException, IOException {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass(className);

		// add this to define a super class to extend
		// cc.setSuperclass(resolveCtClass(MySuperClass.class));

		// add this to define an interface to implement
		cc.addInterface(resolveCtClass(Serializable.class));

		for (Entry<String, Class<?>> entry : properties.entrySet()) {

			cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));

			// add getter
			cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue(), annotationsMap));

			// add setter
			cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
		}
		Class c = cc.toClass();
		cc.writeFile();
		return c;
	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass,
			HashMap<String, HashMap<Class<?>, HashMap<String, ?>>> annotationsMap) throws CannotCompileException {
		String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
				.append("return this.").append(fieldName).append(";").append("}");
		CtMethod method = CtMethod.make(sb.toString(), declaringClass);
		MethodInfo methodInfoGetEid = method.getMethodInfo();
		ConstPool cp = methodInfoGetEid.getConstPool();

		HashMap<Class<?>, HashMap<String, ?>> hashMap = annotationsMap.get(fieldName);
		if (hashMap != null) {
			HashMap<String, ?> anno = hashMap.get(Id.class);
			if (anno != null) {
				for (Entry entry : anno.entrySet()) {
					Annotation annotationNew = new Annotation(Id.class.getName(), cp);
					// annotationNew.addMemberValue(name, new BooleanMemberValue(value, cp));
					AnnotationsAttribute attributeNew = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
					attributeNew.setAnnotation(annotationNew);
					method.getMethodInfo().addAttribute(attributeNew);
				}
			}
			anno = hashMap.get(Column.class);
			if (anno != null) {
				for (Entry entry : anno.entrySet()) {
					Annotation annotationNew = new Annotation(Column.class.getName(), cp);
					// annotationNew.addMemberValue(name, new BooleanMemberValue(value, cp));
					AnnotationsAttribute attributeNew = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
					attributeNew.setAnnotation(annotationNew);
					method.getMethodInfo().addAttribute(attributeNew);
				}
			}
			anno = hashMap.get(GeneratedValue.class);
			if (anno != null) {
				for (Entry entry : anno.entrySet()) {
					EnumMemberValue memberValue = new EnumMemberValue(cp);
					memberValue.setType(GenerationType.class.getName());
					memberValue.setValue("AUTO");
					Annotation annotationNew = new Annotation(GeneratedValue.class.getName(), cp);
					annotationNew.addMemberValue("strategy", memberValue);
					AnnotationsAttribute attributeNew = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
					attributeNew.setAnnotation(annotationNew);
					method.getMethodInfo().addAttribute(attributeNew);
				}
			}
		}
		return method;
	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {
		String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
				.append("return this.").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ")
				.append(fieldName).append(")").append("{").append("this.").append(fieldName).append("=")
				.append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtClass resolveCtClass(Class clazz) throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		return pool.get(clazz.getName());
	}
}