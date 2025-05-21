package net.tp2.tp2_orm;

import net.tp2.tp2_orm.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import net.tp2.tp2_orm.entities.Patient;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class Tp2OrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp2OrmApplication.class, args);
	}
	@Bean
	CommandLineRunner start(PatientRepository patientRepository) {
		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			// 1️⃣ Utilisation de @Builder
			Patient p1 = Patient.builder()
					.nom("Ayyoub")
					.dateNaissance(sdf.parse("16-06-2002"))
					.malade(false)
					.score(13)
					.build();
			patientRepository.save(p1);

			// 2️⃣ Utilisation de @AllArgsConstructor
			Patient p2 = new Patient(0, "rashid", sdf.parse("17-02-2020"), false, 9);
			patientRepository.save(p2);

			// 3️⃣ Utilisation de @NoArgsConstructor + setters
			Patient p3 = new Patient();
			p3.setNom("youssi");
			p3.setDateNaissance(sdf.parse("03-03-1985"));
			p3.setMalade(true);
			p3.setScore(10);
			patientRepository.save(p3);
			patientRepository.findAll().forEach(System.out::println);
			Patient p4= patientRepository.findById(p1.getId());
			System.out.println("Consulter un patient\n "+p4.toString());
			System.out.println("Chercher des patients\n");
			patientRepository.findByNomIgnoreCase("a").forEach(System.out::println);
			p4.setNom("ait mansour");
			patientRepository.save(p4);
			System.out.println("Mettre à jour  un patient\n"+p4.toString());
			System.out.println("supprimer  un patient\n");
			patientRepository.delete(p4);
			patientRepository.findAll().forEach(System.out::println);



		};
	}
}
