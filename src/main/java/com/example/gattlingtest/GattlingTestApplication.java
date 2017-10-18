package com.example.gattlingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class GattlingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GattlingTestApplication.class, args);
	}

	@RestController
	static class Ctrlo {
	    Random r = new Random();

		@GetMapping("/go/{data}")
		public String go(@PathVariable String data) {
		    try {
                TimeUnit.SECONDS.sleep(r.nextInt(10));
            } catch (Exception e ) {

            }
		    return data;
		};
	}
}
