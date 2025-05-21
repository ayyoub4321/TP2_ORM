package net.tp2.tp2_orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import net.tp2.tp2_orm.entities.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomIgnoreCase(String name);
    Patient findById(long id);


}
