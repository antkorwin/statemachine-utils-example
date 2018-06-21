package com.antkorwin.smutilssample;

import com.antkorwin.statemachineutils.resolver.EnableStateMachineResolver;
import com.antkorwin.statemachineutils.wrapper.EnableStateMachineWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableStateMachineResolver
@EnableStateMachineWrapper
public class StateMachineUtilsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(StateMachineUtilsExampleApplication.class, args);
	}
}
