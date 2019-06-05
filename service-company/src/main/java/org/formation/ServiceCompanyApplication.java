package org.formation;

import java.util.stream.Stream;

import org.formation.dao.CompanyRepository;
import org.formation.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ServiceCompanyApplication {
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(ServiceCompanyApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompanyRepository companyRepository) {
		repositoryRestConfiguration.exposeIdsFor(Company.class);
		return args->{
			Stream.of("A","B","C").forEach(cn-> {
				companyRepository.save(new Company(null,cn,100+Math.random()*900));	
			});
			companyRepository.findAll().forEach(System.out::println);
		};
	}
}
