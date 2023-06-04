package com.prolab;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ProLabIndiaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProLabIndiaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		String jarFilePath = ProLabIndiaApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String jarDirPath = new File(jarFilePath).getParent();
		System.out.println("JAR file home directory path: " + jarDirPath);
		
		Path currentDirectory = Paths.get("").toAbsolutePath();
        System.out.println("Current Directory: " + currentDirectory);

        // Go back to the parent directory
        Path parentDirectory = currentDirectory.getParent();
        System.out.println("Parent Directory: " + parentDirectory);
		*/
	}

}
