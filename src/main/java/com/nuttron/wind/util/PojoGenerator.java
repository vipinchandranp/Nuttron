package com.nuttron.wind.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javassist.bytecode.annotation.StringMemberValue;

public class PojoGenerator {

	/*public static Class generate(String className, Map<String, Class<?>> properties)
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
	}*/

	public static Class generate(String className, Map<String, Class<?>> properties)
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
			cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));

			// add setter
			cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
		}
		
		/*Map<String, Object> valuesMap = new HashMap<>();
		valuesMap.put("name", "Entity");
		RuntimeAnnotations.putAnnotation(clazz, Entity.class, new HashMap<>());
		RuntimeAnnotations.putAnnotation(clazz, org.hibernate.annotations.DynamicUpdate.class, new HashMap<>());
		RuntimeAnnotations.putAnnotation(clazz, org.hibernate.annotations.Table.class, valuesMap);*/
		
		ConstPool constpool = cc.getClassFile().getConstPool();
		AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
	    Annotation entityAnno = new Annotation(Entity.class.getName(), constpool);
	    Annotation tableAnno = new Annotation(org.hibernate.annotations.Table.class.getName(), constpool);
	    tableAnno.addMemberValue("name", new StringMemberValue("entity", constpool));
	    attr.addAnnotation(tableAnno);
	    attr.addAnnotation(entityAnno);
	    // add the annotation to the class
	    cc.getClassFile().addAttribute(attr);
		
		cc.writeFile();
		Class c = cc.toClass();
		return c;
	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass) throws CannotCompileException {
		CtMethod method = null;
		if(fieldName.equalsIgnoreCase("id")) {
			String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			StringBuffer sb = new StringBuffer();
			sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
					.append("return this.").append(fieldName).append(";").append("}");
			method = CtMethod.make(sb.toString(), declaringClass);
			MethodInfo methodInfoGetEid = method.getMethodInfo();
			ConstPool cp = methodInfoGetEid.getConstPool();

			Annotation idAnnotation = new Annotation(Id.class.getName(), cp);
			
			Annotation columnAnnotation = new Annotation(Column.class.getName(), cp);
			columnAnnotation.addMemberValue("name", new StringMemberValue(fieldName, cp));
			
			
			EnumMemberValue memberValue = new EnumMemberValue(cp);
			memberValue.setType(GenerationType.class.getName());
			memberValue.setValue("AUTO");
			Annotation genTypeAnnotation = new Annotation(GeneratedValue.class.getName(), cp);
			genTypeAnnotation.addMemberValue("strategy", memberValue);
			
			AnnotationsAttribute genAttribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
			genAttribute.addAnnotation(idAnnotation);
			genAttribute.addAnnotation(genTypeAnnotation);
			genAttribute.addAnnotation(columnAnnotation);
			method.getMethodInfo().addAttribute(genAttribute);
		}else {
			String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			StringBuffer sb = new StringBuffer();
			sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
					.append("return this.").append(fieldName).append(";").append("}");
			method = CtMethod.make(sb.toString(), declaringClass);
			MethodInfo methodInfoGetEid = method.getMethodInfo();
			ConstPool cp = methodInfoGetEid.getConstPool();

			Annotation columnAnnotation = new Annotation(Column.class.getName(), cp);
			columnAnnotation.addMemberValue("name", new StringMemberValue(fieldName, cp));
			AnnotationsAttribute columnAttribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
			columnAttribute.setAnnotation(columnAnnotation);
			method.getMethodInfo().addAttribute(columnAttribute);
		}
		
		
		return method;
	}

	/*private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {
		String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
				.append("return this.").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}*/

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