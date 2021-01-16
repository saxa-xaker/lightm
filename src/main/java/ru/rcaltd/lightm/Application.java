package ru.rcaltd.lightm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.rcaltd.lightm.services.ultraSoundSensorService.MainCycleService;

@EnableAsync
@SpringBootApplication
public class Application {

	static MainCycleService mainCycleService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		mainCycleService.mainCycleStart();
	}
}