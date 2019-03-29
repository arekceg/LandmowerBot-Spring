package com.arek.lawnmowerbot;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
@AllArgsConstructor
public class Runner {
	private final DescriptionGenerator descriptionGenerator;
	private final ImageGenerator imageGenerator;
	private final ImagePublisher imagePublisher;

	@PostConstruct
	public void run() throws Exception {
		File randomImage = imageGenerator.getRandomImage();
		String randomDescription = descriptionGenerator.makeDescription();
		imagePublisher.publishImage(randomImage, randomDescription);
	}
}
