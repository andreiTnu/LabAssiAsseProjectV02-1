package org.example;

import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{


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
    public void addAssignmentInvalidID() {

        Tema new_tema = new Tema("","O tema de test", 5, 4);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Numar tema invalid!");
        }
        service.deleteTema("1234");
    }

}
