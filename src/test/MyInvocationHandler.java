package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{

	Object internalObject;
	
	public MyInvocationHandler(Object o) {
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
		System.out.print(")");
		return m.invoke(internalObject, args);
	}
}
