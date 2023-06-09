package org.java.demo;

import org.java.demo.auth.pojo.Role;
import org.java.demo.auth.pojo.User;
import org.java.demo.auth.serv.RoleServ;
import org.java.demo.auth.serv.UserServ;
import org.java.demo.pojo.Category;
import org.java.demo.pojo.Picture;
import org.java.demo.serv.CategoryServ;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PictureServ pictureServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role roleAdmin = new Role("ADMIN");
		
		roleServ.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userServ.save(userAdmin);
		
		Category c1 = new Category("Still life");
		Category c2 = new Category("Street photography");
		Category c3 = new Category("Astrofotografia");
		Category c4 = new Category("Reportage");
		Category c5 = new Category("Viaggio");
		Category c6 = new Category("Subacquea");
		Category c7 = new Category("Paesaggistica");
		Category c8 = new Category("Ritrattistica");
		Category c9 = new Category("Naturalistica");
		
		categoryServ.save(c1);
		categoryServ.save(c2);
		categoryServ.save(c3);
		categoryServ.save(c4);
		categoryServ.save(c5);
		categoryServ.save(c6);
		categoryServ.save(c7);
		categoryServ.save(c8);
		categoryServ.save(c9);
		
		Picture p1 = new Picture("La Meraviglia", "L'incontro di due oceani", "https://picsum.photos/300/200", 
				true, new Category[] {c7, c9});
		Picture p2 = new Picture("Nebulosa", "Nascita di stelle", "https://picsum.photos/300/200", 
				true, c3);
		Picture p3 = new Picture("L'orologio", "Il passare del tempo", "https://picsum.photos/300/200", 
				false, new Category[] {c1, c2, c4});
		Picture p4 = new Picture("La Barriera Corallina", "La convivenza di specie marine", "https://picsum.photos/300/200", 
				true, new Category[] {c7, c9, c6, c5, c4});
		Picture p5 = new Picture("Ritratto di guerra", "Le causalità dei conflitti", "https://picsum.photos/300/200", 
				false);
		
		pictureServ.save(p1);
		pictureServ.save(p2);
		pictureServ.save(p3);
		pictureServ.save(p4);
		pictureServ.save(p5);
	}

}
