package ru.rcaltd.lightm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		try {
			Document doc = Jsoup.connect("http://localhost:8080/mainCycleStart")
					.userAgent("Chrome/4.0.249.0 Safari/532.5")
					.referrer("http://localhost:8080")
					.get();
		} catch (IOException e) {
			//e.printStackTrace();
		}

	}
}