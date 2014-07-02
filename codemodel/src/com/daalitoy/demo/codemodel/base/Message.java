package com.daalitoy.demo.codemodel.base;

import java.util.HashMap;
import java.util.Map;

public class Message {

	private Map<String, Object> objectMap = new HashMap<String, Object>();

	public boolean hasObject(String objName) {
		return (objectMap.containsKey(objName));
	}

	public void setObject(String objName, Object obj) {
		objectMap.put(objName, obj);
	}

	public Object getObject(String objName) {
		return (objectMap.get(objName));
	}

}
