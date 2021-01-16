package ru.rcaltd.lightm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.rcaltd.lightm.services.ControlSensorsService;

@EnableAsync
@SpringBootApplication
public class Application {

	static ControlSensorsService controlSensorsService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		controlSensorsService.mainCycle();
	}
}