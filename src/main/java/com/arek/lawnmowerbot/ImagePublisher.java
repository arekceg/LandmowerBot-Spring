package com.arek.lawnmowerbot;

import com.restfb.*;
import com.restfb.types.GraphResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Files;

@Component
@Slf4j
class ImagePublisher {

	private final FacebookClient fbClient;
	private String pageId;
	@Value("${image.publish}")
	private boolean publishImage;

	public ImagePublisher(@Value("${token}") String token, @Value("${pageId}") String pageId) {
		this.pageId = pageId;
		this.fbClient = new DefaultFacebookClient(token, Version.LATEST);
	}

	void publishImage(File image, String description) throws Exception {
		if (publishImage) {
			GraphResponse imagePublishedResponse =
					fbClient.publish(pageId + "/photos", GraphResponse.class,
							BinaryAttachment.with(image.getName(), Files.readAllBytes((image.toPath()))),
							Parameter.with("message", description));
			log.info("Image published.\n" +
					"ID: " + imagePublishedResponse.getId());
		} else {
			log.info("Didn't publish image cause token is empty");
		}
	}

}
