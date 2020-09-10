package com.everest.samples.core;

import java.util.ArrayList;
import java.util.List;

public class CoreJavaExamples {


	public static void main(String[] args) {
		boolean test[] = new boolean[3];
		testGenericExample();
		System.out.println(testReturnWithTryFinally());
		System.out.println(testReturnWithLayeredTryFinally());
	}
	
	private static void testGenericExample() {
//		List<Parent> elements = new ArrayList<Child>(); // this is compile error
		List<Parent> elements = new ArrayList<Parent>();
	}
	
	static int testReturnWithTryFinally() {
		try {
			throw new Exception();
		} catch (Exception e) {
			return 2;
		} finally {
			//eclipse gives a warning since finally is meant for clean up and not for return statements
			//returns from finally override previous returns and hence not recommended.
			return 3;
		}
	}
	
	static int testReturnWithLayeredTryFinally() {
		try {
			System.out.println("Outer Try");
			try {
				throw new Exception();
			} catch (Exception e) {
				return 2;
			} finally {
				// eclipse gives a warning since finally is meant for clean up and not for
				// return statements
				// returns from finally override previous returns and hence not recommended.
				return 3;
			}
		} finally {
			System.out.println("Outer Finally");
		}

	}
	static class Parent {
		
	}
	
	static class Child extends Parent {
		
	}
}
