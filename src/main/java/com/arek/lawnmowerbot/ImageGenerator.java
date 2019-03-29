package com.arek.lawnmowerbot;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Random;

@Component
class ImageGenerator {
	private ResourcePatternResolver resolver;

	@PostConstruct
	public void setup() {
		ClassLoader cl = this.getClass().getClassLoader();
		resolver = new PathMatchingResourcePatternResolver(cl);
	}

	public File getRandomImage() {
		Resource[] resources;
		try {
			resources = resolver.getResources("/img/*.jpg");
			Random r = new Random();
			Resource resource = resources[r.nextInt(resources.length)];
			return resource.getFile();
		} catch (IOException e) {
			throw new RuntimeException("Can't load resources", e);
		}
	}
}
