package com.arek.lawnmowerbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static java.lang.Math.random;

@Component
class DescriptionGenerator {

	@Value("classpath:words/names.txt")
	private Resource names;
	@Value("classpath:words/adj.txt")
	private Resource adj;
	@Value("classpath:words/adv.txt")
	private Resource adv;
	@Value("classpath:words/nouns.txt")
	private Resource nouns;

	private Random rand = new Random();

	String makeDescription() throws IOException {
		return ("Name :" + makeName() + "\n" +
				"Nickname in High School: " + makeNickname());
	}

	private String makeName() throws IOException {
		StringBuilder sb = new StringBuilder();

		sb.append(pickFrom(names)).append(pickConjunction());
		if (sb.charAt(sb.length() - 1) == ' '){
			if(Math.random() <= 0.3) sb.append(pickFrom(adv));
			else sb.append(pickFrom(adv)).append(pickFrom(adj));
		}
		return sb.toString();
	}

	private String makeNickname() throws IOException {
		StringBuilder sb = new StringBuilder();
		double chance = Math.random();

		if(chance <= 0.3) sb.append(pickFrom(adj)).append(pickFrom(nouns));
		else if(chance <=0.7) sb.append(pickFrom(adj));
		else sb.append (pickFrom(nouns));

		return sb.toString();
	}

	private String pickFrom(Resource resource) throws IOException {
		File file = resource.getFile();
		List<String> options = Files.readAllLines(file.toPath());
		return options.get(rand.nextInt(options.size()));
	}

	private String pickConjunction() {
        StringBuilder conj = new StringBuilder();
		return(conj.append((random()<0.7 ? " the " : ""))).toString();
	}
}
