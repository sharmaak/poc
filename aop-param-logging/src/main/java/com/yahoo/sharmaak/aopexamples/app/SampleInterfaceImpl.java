package com.yahoo.sharmaak.aopexamples.app;

public class SampleInterfaceImpl implements SampleInterface {

	@Override
	public void hello(String text, Long number) {
		System.out.println("\t\t> " + getClass() + ".hello()");
	}

	@Override
	public void show(int[] numbers) {
		System.out.println("\t\t> " + getClass() + ".show()");
	}
}
