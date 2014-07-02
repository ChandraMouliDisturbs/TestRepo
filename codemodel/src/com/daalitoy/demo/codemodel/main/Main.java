package com.daalitoy.demo.codemodel.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.daalitoy.demo.codemodel.base.Message;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import com.sun.codemodel.writer.FileCodeWriter;

public class Main {

	public static void main(String[] args) throws JClassAlreadyExistsException,
			Exception {

		String packageName = "com.acme.messages";
		String className = "MailMessage";

		JCodeModel codeModel = new JCodeModel();

		// define a class
		JDefinedClass jDefClass = codeModel._class(packageName + "."
				+ className);

		// our class extends our Message class
		jDefClass._extends(Message.class);

		// for simplicity lets read the property names
		// from an array and assume them to be of String types
		String[] classProperties = new String[] { "Recipient", "Content" };

		JMethod constructor = jDefClass.constructor(JMod.PUBLIC);
		constructor.param(String.class, "recipient");
		constructor.param(String.class, "content");
		constructor.body().invoke("setObject").arg(constructor.params().get(0))
				.arg(constructor.params().get(1));

		// for each property, lets create setters getters and has methods

		for (String propertyName : classProperties) {

			// setter method
			String methodName = "set" + propertyName;
			JMethod setterMethod = jDefClass.method(JMod.PUBLIC,
					jDefClass.unboxify(), methodName);
			// setter method parameters
			setterMethod.param(JMod.FINAL, String.class, "objName");
			setterMethod.param(JMod.FINAL, Object.class, "objValue");

			// you can also adds javadoc's
			setterMethod.javadoc().add("setter method for " + propertyName);

			// body of the method
			setterMethod.body().invoke("setObject")
					.arg(setterMethod.params().get(0))
					.arg(setterMethod.params().get(1));

			// I'm building the setter method using the builder
			// pattern, you can drop the following statement if the
			// return type is void
			setterMethod.body()._return(JExpr._this());

			// getter method
			JMethod getterMethod = jDefClass.method(JMod.PUBLIC, String.class,
					"get" + propertyName);
			getterMethod.param(JMod.FINAL, String.class, "objName");

			getterMethod.body()._return(
					JExpr.cast(codeModel.parseType("String"), (JExpr
							.invoke("getObject").arg(getterMethod.params().get(
							0)))));

			// and finally the has method

			JMethod hasMethod = jDefClass.method(JMod.PUBLIC, boolean.class,
					"has" + propertyName);
			hasMethod.param(JMod.FINAL, String.class, "objName");
			hasMethod.body()._return(
					JExpr.invoke("hasObject").arg(hasMethod.params().get(0)));

		}

		// left hand side of the definition
		JClass jListClass = codeModel.ref(List.class).narrow(String.class);
		// right hand side
		JClass arrayListClass = codeModel.ref(ArrayList.class).narrow(
				String.class);
		JFieldVar var = jDefClass.field(JMod.PRIVATE | JMod.STATIC, jListClass,
				"fieldNamesList");
		var.init(JExpr._new(arrayListClass));

		JBlock staticBlock = jDefClass.init();
		// initialize the list
		for (String propertyName : classProperties) {
			staticBlock.add(var.invoke("add").arg(JExpr.lit(propertyName)));

		}

		JMethod toStringMethod = jDefClass.method(JMod.PUBLIC,
				codeModel.parseType("String"), "toString");

		JVar builderVar = toStringMethod.body().decl(
				codeModel.parseType("StringBuilder"), "builder",
				JExpr._new(codeModel.ref("StringBuilder")));

		// or alternatively
		// toStringMethod.body().assign(builderVar,
		// JExpr._new(codeModel.ref("StringBuilder")));

		JForLoop forLoop = toStringMethod.body()._for();
		JVar loopVar = forLoop.init(codeModel.INT, "i", JExpr.lit(0));

		forLoop.test(loopVar.lt(JExpr.lit(classProperties.length)));
		forLoop.update(loopVar.assignPlus(JExpr.lit(1)));

		JExpression jExpr=JExpr.direct("String.format(\"%s : %s\\n\",fieldNamesList.get(i),getObject(fieldNamesList.get(i)))");
		forLoop.body().add(builderVar.invoke("append").arg(jExpr));

		toStringMethod.body()._return(builderVar.invoke("toString"));

		// lets save the generated classes to a directory
		// on the system
		codeModel.build(new FileCodeWriter(new File("d:\\Temp")));

	}
}
