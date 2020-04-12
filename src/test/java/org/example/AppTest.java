package org.example;

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
    public void addStudentEmptyId() {

        Student new_student = new Student("","Mihai",937,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id incorect!");
        }
    }

    @Test
    public void addStudentBelowZeroGroup() {

        Student new_student = new Student("2133","Mihai",-4,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Grupa incorecta!");
        }
    }

    @Test
    public void addStudentEmptyEmail() {

        Student new_student = new Student("2133","Mihai",937,"");


        try{
            service.addStudent(new_student);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Email incorect!");
        }
    }

    @Test
    public void addStudentNullEmail() {

        Student new_student = new Student("2133","Mihai",937,null);


        try{
            service.addStudent(new_student);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Email incorect!");
        }
    }

    @Test
    public void addStudentNullName() {

        Student new_student = new Student("2133",null,937,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Nume incorect!");
        }
    }

    @Test
    public void addStudentEmptyName() {

        Student new_student = new Student("2133","",937,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Nume incorect!");
        }
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

    @Test
    public void addAssignementTestSuccess(){
        Tema new_tema = new Tema("999","test", 10, 8);

        service.addTema(new_tema);
        assertEquals(service.findTema("999"),new_tema);

        service.deleteTema("999");
    }
    @Test
    public void addAssignementTestFailID(){
        Tema new_tema = new Tema("","test", 99, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Numar tema invalid!");
        }

    }
    @Test
    public void addAssignementTestFailDescription(){
        Tema new_tema = new Tema("1111","", 99, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Descriere invalida!");
        }

    }
    @Test
    public void addAssignementTestFailDeadline(){
        Tema new_tema = new Tema("1111","Test", 0, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Deadlineul trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementTestFailDeadlineMinus(){
        Tema new_tema = new Tema("1111","Test", -100, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Deadlineul trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementTestFailDeadlinePlus(){
        Tema new_tema = new Tema("1111","Test", 16, 15);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Deadlineul trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementTestFailPrimire(){
        Tema new_tema = new Tema("1111","Test", 10, 0);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Saptamana primirii trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementTestFailPrimireMinus(){
        Tema new_tema = new Tema("1111","Test", 10, -100);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Saptamana primirii trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementTestFailPrimirePlus(){
        Tema new_tema = new Tema("1111","Test", 10, 15);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Saptamana primirii trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementTestFailDeadlinePrimireSwitch(){
        Tema new_tema = new Tema("1111","Test", 5, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Primire < Deadline!");
        }

    }

    @Test
    public void addAssignmentIdString(){
        Tema new_tema = new Tema("aba","test", 10, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id trebuie sa fie numar!");
        }

    }

    @Test
    public void addAssignmentIdNegativ(){
        Tema new_tema = new Tema("-100","test", 10, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id trebuie sa fie pozitiv!");
        }

    }

    @Test
    public void addAssignmentIdStringNumar(){
        Tema new_tema = new Tema("10a","test", 10, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id trebuie sa fie numar!");
        }

    }


}
