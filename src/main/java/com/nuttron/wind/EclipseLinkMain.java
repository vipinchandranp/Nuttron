package com.nuttron.wind;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.nuttron.wind.util.FileUtils;
import com.nuttron.wind.util.PojoGenerator;
import com.nuttron.wind.util.RuntimeAnnotations;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class EclipseLinkMain {

	public static void main(String[] args)
			throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

		System.out.println(FileUtils.getFileContent("F:\\Work\\GitRepo\\Nuttron\\src\\main\\resources\\A.json"));

		String fileContent = FileUtils.getFileContent("F:\\Work\\GitRepo\\Nuttron\\src\\main\\resources\\A.json");

		/*
		 * TestAnnotation annotation =
		 * TestClass.class.getAnnotation(TestAnnotation.class);
		 * System.out.println("TestClass annotation before:" + annotation);
		 * 
		 * Map<String, Object> valuesMap = new HashMap<>(); valuesMap.put("value",
		 * "some String"); RuntimeAnnotations.putAnnotation(TestClass.class,
		 * TestAnnotation.class, valuesMap);
		 * 
		 * annotation = TestClass.class.getAnnotation(TestAnnotation.class);
		 * System.out.println("TestClass annotation after:" + annotation);
		 */

		Map<String, Class<?>> props = new HashMap<String, Class<?>>();
		props.put("id", Integer.class);
		props.put("value", String.class);

		Class<?> clazz = PojoGenerator.generate("com.nuttron.wind.EntityMain", props);

		Map<String, Object> valuesMap = new HashMap<>();
		valuesMap.put("name", "Entity");
		RuntimeAnnotations.putAnnotation(clazz, Entity.class, new HashMap<>());
		RuntimeAnnotations.putAnnotation(clazz, org.hibernate.annotations.DynamicUpdate.class, new HashMap<>());
		RuntimeAnnotations.putAnnotation(clazz, org.hibernate.annotations.Table.class, valuesMap);

		System.out.println("Entity annotation after:" + clazz.getAnnotation(Entity.class));
		System.out.println(
				"Entity annotation after:" + clazz.getAnnotation(org.hibernate.annotations.DynamicUpdate.class));
		System.out.println("Entity annotation after:" + clazz.getAnnotation(org.hibernate.annotations.Table.class));

		for (final Method method : clazz.getDeclaredMethods()) {
			System.out.println(method);
			java.lang.annotation.Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
			for (java.lang.annotation.Annotation anotation : declaredAnnotations) {
				System.out.println(anotation);
			}
		}
		Object obj = clazz.newInstance();
		System.out.println("Clazz: " + clazz);
		System.out.println("Object: " + obj);
		System.out.println("Serializable? " + (obj instanceof Serializable));

		/*
		 * ClassPool pool = ClassPool.getDefault(); CtClass cc =
		 * pool.getCtClass(clazz.getName()); CtMethod sayHelloMethodDescriptor =
		 * cc.getDeclaredMethod("getValue"); ClassFile ccFile = cc.getClassFile();
		 * ConstPool constpool = ccFile.getConstPool();
		 * 
		 * AnnotationsAttribute attr = new AnnotationsAttribute(constpool,
		 * AnnotationsAttribute.visibleTag); Annotation annot = new
		 * Annotation(Id.class.getName(), constpool); // annot.addMemberValue("name",
		 * new StringMemberValue("World!! (dynamic // annotation)",
		 * ccFile.getConstPool())); attr.addAnnotation(annot);
		 * sayHelloMethodDescriptor.getMethodInfo().addAttribute(attr);
		 */
		// set property "bar"
		clazz.getMethod("setValue", String.class).invoke(obj, "Hello World!");

		// get property "bar"
		String result = (String) clazz.getMethod("getValue").invoke(obj);

		System.out.println(result);

		//EntityManager em = Persistence.createEntityManagerFactory("eclipselink").createEntityManager();
		//em.persist(obj);

		/*
		 * Configuration configuration = new Configuration().configure();
		 * configuration.addClass(clazz); StandardServiceRegistryBuilder builder = new
		 * StandardServiceRegistryBuilder()
		 * .applySettings(configuration.getProperties()); SessionFactory factory =
		 * configuration.buildSessionFactory(builder.build()); Session session =
		 * factory.openSession(); session.save(obj); session.getTransaction().commit();
		 * session.close();
		 */
		/*
		 * A a = new A(); a.setAValue("fdsd"); em.persist(a);
		 */
		System.exit(0);

	}
}
