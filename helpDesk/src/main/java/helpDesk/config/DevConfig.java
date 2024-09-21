package helpDesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import helpDesk.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB() {
		
		if(value.equals("create")) {
		
			this.dbService.instanciaDB();
		
		}
	}

}
