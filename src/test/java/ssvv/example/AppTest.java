package ssvv.example;

import domain.Student;
import junit.framework.TestCase;
import validation.StudentValidator;

public class AppTest 
    extends TestCase
{
    private StudentValidator validationService = new StudentValidator();

    public void testValidateValidStudent() {
        Student validStudent = new Student("S001", "John Doe", 123);

        try {
            validationService.validate(validStudent);
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

    public void testValidateInvalidID() {
        Student studentWithInvalidID = new Student("", "John Doe", 123);

        try {
            validationService.validate(studentWithInvalidID);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ID invalid! \n");
        }
    }

    public void testValidateInvalidNume() {
        Student studentWithInvalidNume = new Student("S001", "", 123);

        try {
            validationService.validate(studentWithInvalidNume);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Nume invalid! \n");
        }
    }
}
