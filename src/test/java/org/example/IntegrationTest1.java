package org.example;


import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class IntegrationTest1 {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Test
    public void bigBang() {
        addStudent();
        addAssignmentValidID();
        addNotaTestFailNoStudent();
    }

    @Test
    public void addStudent() {

        Student new_student = new Student("2133","Mihai",937,"etc@yahoo.com");

        service.addStudent(new_student);
        assertEquals(service.findStudent("2133"),new_student);

        service.deleteStudent("2133");
    }

    @Test
    public void addAssignmentValidID() {

        Tema new_tema = new Tema("1234","O tema de test", 5, 4);

        service.addTema(new_tema);
        assertEquals(service.findTema("1234"),new_tema);

        service.deleteTema("1234");
    }

    @Test
    public void addNotaTestFailNoStudent(){

        LocalDate date = LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("3"), Integer.parseInt("10"));
        Nota nota_new = new Nota("1111", "1", "2", 11,date);

        try{
            service.addNota(nota_new, "yes bv");
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Valoarea notei nu este corecta!");
        }


        service.deleteNota("1111");


    }
}
