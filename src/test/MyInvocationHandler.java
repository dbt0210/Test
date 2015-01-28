package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler<T> implements InvocationHandler{

	T internalObject;
	
	public MyInvocationHandler(T o) {
		internalObject = o;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		System.out.print(internalObject + "." + m.getName() + "(");
		if (args != null) {
			for (int i = 0; i < args.length; ++i) {
				System.out.print(args[i]+(i + 1 < args.length ? ", " : ""));
			}
		}
		System.out.println(")");
		return m.invoke(internalObject, args);
	}
}
