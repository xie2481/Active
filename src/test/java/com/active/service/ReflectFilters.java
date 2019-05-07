package com.active.service;

import java.lang.reflect.Method;

public class ReflectFilters {

	Class<?> classType = this.getClass();

	public Object DoMethod(String mName, Object object) {
		try {
			Method method;
			Object obj;
			if (object != null) {
				method = classType.getMethod(mName, new Class[] { object.getClass() });
				obj = method.invoke(classType.newInstance(), new Object[] { object });
				if (obj != null) {
					return obj;
				}
			} else {
				method = classType.getMethod(mName);
				obj = method.invoke(classType.newInstance());
				if (obj != null) {
					return obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
