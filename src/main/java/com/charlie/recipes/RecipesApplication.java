package com.charlie.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(RecipesApplication.class, args);
	}
}
