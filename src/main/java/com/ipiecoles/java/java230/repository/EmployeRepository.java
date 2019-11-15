package com.ipiecoles.java.java230.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipiecoles.java.java230.model.Employe;

public interface EmployeRepository extends PagingAndSortingRepository<Employe,Long>{
	
	Employe findByMatricule(String matricule);
	List<Employe> findByNomAndPrenom(String nom, String prenom);
	List<Employe> findByNomIgnoreCase(String nom);
	List<Employe> findByDateEmbaucheBefore(LocalDate dateEmbauche);
	List<Employe> findByDateEmbaucheAfter(LocalDate dateEmbauche);
	List<Employe> findBySalaireGreaterThanOrderBySalaireDesc(Double salaire);
	
	Page<Employe> findByNomIgnoreCase(String nom, Pageable pageable);
	
	@Query("select e from Employe e where lower(e.prenom) = lower(:nomOuPrenom) or lower(e.nom) = lower(:nomOuPrenom)") 
	List<Employe> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOuPrenom);
	
	@Query(value="SELECT * FROM Employe WHERE salaire > (SELECT avg(e2.salaire) FROM Employe e2)", nativeQuery = true)
	List<Employe> findEmployePlusRiches();
	
}