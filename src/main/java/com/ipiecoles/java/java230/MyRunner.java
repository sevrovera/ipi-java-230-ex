package com.ipiecoles.java.java230;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.java230.model.Employe;
import com.ipiecoles.java.java230.repository.EmployeRepository;

@Component
public class MyRunner implements CommandLineRunner {

	
	@Autowired
	private EmployeRepository employeRepository;
	
    @Override
    public void run(String... strings) throws Exception {
    	System.out.println("Nombre emp" + employeRepository.count());
    	Employe emp = employeRepository.findOne(5L);
    	print(emp.toString());
    	emp.setNom(emp.getNom().toUpperCase());
    	employeRepository.save(emp); // demande persistence des données
    	
    	/*Employe newEmp = new Employe ("Doe", "John", "M98989", LocalDate.now(), 2000d);
    	print(newEmp.toString());
    	newEmp = employeRepository.save(newEmp);
    	print(newEmp.toString());*/
    	
    	/*List<Employe> emps = (ArrayList)employeRepository.findAll();
    	 * for (Employe e : emps){
    	 * print(e.toString());
    	 */
    	
    	// employeRepository.delete(2504L)
    	
    	
    	Page<Employe> emps = employeRepository.findAll(new PageRequest(0,10,Sort.Direction.ASC, "matricule"));
    			for (Employe e : emps) {
    				print(e.toString());
    			}
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}
