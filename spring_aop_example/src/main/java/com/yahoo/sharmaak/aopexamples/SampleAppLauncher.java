package com.yahoo.sharmaak.aopexamples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yahoo.sharmaak.aopexamples.app.SampleInterface;

public class SampleAppLauncher {

	private static final String SPRING_CONFIG_LOCATION = "classpath:./springconfig.xml";
	
	public void execute() {

		ApplicationContext context = 
			new FileSystemXmlApplicationContext(SPRING_CONFIG_LOCATION);
		SampleInterface simpleInterface = 
			(SampleInterface) context.getBean("proxyBean");
	
		simpleInterface.hello();
		simpleInterface.show();
	}

	public static void main(String[] args) {
		SampleAppLauncher launcher = new SampleAppLauncher();
		launcher.execute();
	}

}
