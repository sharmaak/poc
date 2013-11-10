package com.yahoo.sharmaak.aopexamples.app;

public class SampleInterfaceImpl implements SampleInterface {

	@Override
	public void hello() {
		System.out.println("\t\t> " + getClass() + ".hello()");
	}

	@Override
	public void show() {
		System.out.println("\t\t> " + getClass() + ".show()");
	}

}
