package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class TestClass {

	public static void main(String[] args) {
		Class[] interfaces = String.class.getInterfaces();		
		Set<Object> set = new TreeSet<>();
		ArrayList<Object> strings = new ArrayList<>();
		for (int i = 0; i < 5; ++i) {
			strings.add(Proxy.newProxyInstance(null, interfaces, new MyInvocationHandler("String_" + i)));
		}
		set.addAll(strings);
	}

}
