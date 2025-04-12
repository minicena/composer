package com.br.composer.composer;

import org.springframework.boot.SpringApplication;

public class TestComposerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ComposerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
