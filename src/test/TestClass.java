package test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TestClass {
	
	public static void test1() {
		//get interfaces and create data structure
		Class<?>[] interfaces = String.class.getInterfaces();		
		Set<Object> set = new TreeSet<>();
		ArrayList<Object> strings = new ArrayList<>();
		//add proxies to data structure
		for (int i = 0; i < 5; ++i) {
			strings.add(Proxy.newProxyInstance(null, interfaces, new MyInvocationHandler<String>("String_" + i)));
		}
		//test
		set.addAll(strings);
	}
	
	public static void test2() {
		//get interfaces and create data structure
		Class<?>[] ifs = Integer.class.getInterfaces();
		Object[] elements = new Object[1000];
		//add proxies to data structure
		for (int i = 0; i < elements.length; i++) {
			Integer value = i + 1;
			Object proxy = Proxy.newProxyInstance(null, ifs,
					new MyInvocationHandler<Integer>(value));
			elements[i] = proxy;
		}
		//test
		Integer key = new Random().nextInt(elements.length) + 1;
		int result = Arrays.binarySearch(elements, key);
		if (result >= 0)
			System.out.println(elements[result]);
	}

	public static void main(String[] args) {
		test2();
		test1(); //throws:
//		Exception in thread "main" java.lang.reflect.UndeclaredThrowableException
//		at com.sun.proxy.$Proxy1.compareTo(Unknown Source)
//		at java.util.TreeMap.compare(Unknown Source)
//		at java.util.TreeMap.put(Unknown Source)
//		at java.util.TreeSet.add(Unknown Source)
//		at java.util.AbstractCollection.addAll(Unknown Source)
//		at java.util.TreeSet.addAll(Unknown Source)
//		at test.TestClass.test1(TestClass.java:22)
//		at test.TestClass.main(TestClass.java:45)
//	Caused by: java.lang.reflect.InvocationTargetException
//		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//		at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
//		at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
//		at java.lang.reflect.Method.invoke(Unknown Source)
//		at test.MyInvocationHandler.invoke(MyInvocationHandler.java:23)
//		... 8 more
//	Caused by: java.lang.ClassCastException: com.sun.proxy.$Proxy1 cannot be cast to java.lang.String
//		at java.lang.String.compareTo(Unknown Source)
//		... 13 more
	}

}
