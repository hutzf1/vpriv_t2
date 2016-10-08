/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2.crypto;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Rolf Haenni <rolf.haenni@bfh.ch>
 */
public class Example {

	private static final PrintStream OUT = System.out;
	private static final String LABEL_SEP = ": ";
	private static final String ITEM_SEP = ", ";
	private static final String UNDERLINE = "=";
	private static final String INDENT = "   ";
	private static int indentLevel = 0;
	private static int labelLength = 0;

	public static void runExamples() {
		// Calling method is at index 2 on current stack trace
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Class<?> classType = null;
		try {
			classType = Class.forName(className);
		} catch (ClassNotFoundException ex) {
		}
		if (classType != null) {
			Method[] methods = classType.getDeclaredMethods();
			Comparator<Method> comparator = new Comparator<Method>() {
				@Override
				public int compare(Method m1, Method m2) {
					return m1.getName().compareTo(m2.getName());
				}
			};
			// methods are not automatically sorted according to their appearance
			Arrays.sort(methods, comparator);
			for (Method method : methods) {
				Example.resetindentLevel();
				Example.resetLabelLength();
				// test necessary to exclude the method "main"
				if (!method.getName().equals("main")) {
					Example.printTitle(method.getName().toUpperCase());
					try {
						method.invoke(null);
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
						Example.printLine("RUNTIME ERROR");
					}
					Example.printLine();
				}
			}
		}
	}

	public static void increaseIndentLevel() {
		Example.indentLevel++;
	}

	public static void decreaseIndentLevel() {
		Example.indentLevel--;
	}

	public static void resetindentLevel() {
		Example.indentLevel = 0;
	}

	public static void setLabelLength(String string) {
		Example.labelLength = string.length();
	}

	public static void setLabelLength(int length) {
		Example.labelLength = length;
	}

	public static void resetLabelLength() {
		Example.labelLength = 0;
	}

	public static void print(Object object) {
		OUT.print(object);
	}

	public static void printLine() {
		OUT.println();
	}

	public static void printIndent() {
		for (int i = 0; i < Example.indentLevel; i++) {
			Example.print(Example.INDENT);
		}
	}

	public static void printLabel(String label) {
		Example.print(label);
		for (int i = 0; i < Example.labelLength - label.length(); i++) {
			Example.print(" ");
		}
		Example.print(Example.LABEL_SEP);
	}

	public static void printLabelLine(String label) {
		Example.print(label);
		Example.printLine(Example.LABEL_SEP);
	}

	public static void printTitle(String title) {
		Example.printLine(title);
		for (int i = 0; i < title.length(); i++) {
			Example.print(Example.UNDERLINE);
		}
		Example.printLine();
	}

	public static void printLine(Object object) {
		Example.printIndent();
		Example.print(object);
		Example.printLine();
	}

	public static void printLine(String label, Object object) {
		Example.printIndent();
		Example.printLabel(label);
		Example.print(object);
		Example.printLine();
	}

	public static void printLine(Object... objects) {
		Example.printIndent();
		String sep = "";
		for (Object object : objects) {
			Example.print(sep + object);
			sep = Example.ITEM_SEP;
		}
		Example.printLine();
	}

	public static void printLine(String label, Object... objects) {
		Example.printIndent();
		Example.printLabel(label);
		String sep = "";
		for (Object object : objects) {
			Example.print(sep + object);
			sep = Example.ITEM_SEP;
		}
		Example.printLine();
	}

	public static void printLines(Object object) {
		if (object instanceof Iterable) {
			Example.printLines((Iterable) object);
		} else {
			Example.printLines(new Object[]{object});
		}
	}

	public static void printLines(String label, Object object) {
		if (object instanceof Iterable) {
			Example.printLines(label, (Iterable) object);
		} else {
			Example.printLines(label, new Object[]{object});
		}
	}

	public static void printLines(Object... objects) {
		for (Object object : objects) {
			Example.printLine(object);
		}
	}

	public static void printLines(String label, Object... objects) {
		Example.printLabelLine(label);
		Example.increaseIndentLevel();
		Example.printLines(objects);
		Example.decreaseIndentLevel();
	}

	public static void printLines(Iterable<?> objects) {
		for (Object object : objects) {
			Example.printLine(object);
		}
	}

	public static void printLines(String label, Iterable<?> objects) {
		Example.printLabelLine(label);
		Example.increaseIndentLevel();
		Example.printLines(objects);
		Example.decreaseIndentLevel();
	}

}