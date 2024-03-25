package ssvv.example;

import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import validation.StudentValidator;
import validation.TemaValidator;

public class AppTest 
    extends TestCase
{
    private StudentValidator validationService = new StudentValidator();
    private TemaValidator temaValidator = new TemaValidator();

    public void testValidateTemaWithInvalidID() {
        Tema tema = new Tema(null, "asd", 2,1);
        try {
            temaValidator.validate(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ID invalid! \n");
        }
    }

    public void testValidateTemaWithInvalidEndDate() {
        Tema tema = new Tema("asd", "asd", -1,1);
        try {
            temaValidator.validate(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Deadline invalid! \n");
        }
    }

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

    public void testSaveStudentValid() {
        Student validStudent = new Student("S001", "John Doe", 500);

        try {
            validationService.validate(validStudent);
        } catch (Exception e) {
            fail("Unexpected exception was thrown for valid student");
        }
    }

    // Test case for Null ID, valid name, valid group number
    public void testSaveStudentNullID() {
        Student studentWithNullID = new Student(null, "John Doe", 500);

        try {
            validationService.validate(studentWithNullID);
            fail("Expected ValidationException was not thrown for null ID");
        } catch (Exception e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
    }

    // Test case for Empty ID, valid name, valid group number
    public void testSaveStudentEmptyID() {
        Student studentWithEmptyID = new Student("", "John Doe", 500);

        try {
            validationService.validate(studentWithEmptyID);
            fail("Expected ValidationException was not thrown for empty ID");
        } catch (Exception e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
    }

    // Test case for Valid ID, null name, valid group number
    public void testSaveStudentNullName() {
        Student studentWithNullName = new Student("S001", null, 500);

        try {
            validationService.validate(studentWithNullName);
            fail("Expected ValidationException was not thrown for null name");
        } catch (Exception e) {
            assertEquals("Nume invalid! \n", e.getMessage());
        }
    }

    // Test case for Valid ID, empty name, valid group number
    public void testSaveStudentEmptyName() {
        Student studentWithEmptyName = new Student("S001", "", 500);

        try {
            validationService.validate(studentWithEmptyName);
            fail("Expected ValidationException was not thrown for empty name");
        } catch (Exception e) {
            assertEquals("Nume invalid! \n", e.getMessage());
        }
    }

    // Test case for Valid ID, valid name, group number at lower boundary (110)
    public void testSaveStudentGroupAtLowerBoundary() {
        Student studentWithGroupAtLowerBoundary = new Student("S001", "John Doe", 111);

        try {
            validationService.validate(studentWithGroupAtLowerBoundary);
        } catch (Exception e) {
            fail("Unexpected exception was thrown for group at lower boundary");
        }
    }

    // Test case for Valid ID, valid name, group number at upper boundary (938)
    public void testSaveStudentGroupAtUpperBoundary() {
        Student studentWithGroupAtUpperBoundary = new Student("S001", "John Doe", 937);

        try {
            validationService.validate(studentWithGroupAtUpperBoundary);
        } catch (Exception e) {
            fail("Unexpected exception was thrown for group at upper boundary");
        }
    }

    // Test case for Valid ID, valid name, group number just below lower boundary (109)
    public void testSaveStudentGroupJustBelowLowerBoundary() {
        Student studentWithGroupJustBelowLowerBoundary = new Student("S001", "John Doe", 109);

        try {
            validationService.validate(studentWithGroupJustBelowLowerBoundary);
            fail("Expected ValidationException was not thrown for group just below lower boundary");
        } catch (Exception e) {
            assertEquals("Grupa invalida! \n", e.getMessage());
        }
    }

    // Test case for Valid ID, valid name, group number just above upper boundary (939)
    public void testSaveStudentGroupJustAboveUpperBoundary() {
        Student studentWithGroupJustAboveUpperBoundary = new Student("S001", "John Doe", 939);

        try {
            validationService.validate(studentWithGroupJustAboveUpperBoundary);
            fail("Expected ValidationException was not thrown for group just above upper boundary");
        } catch (Exception e) {
            assertEquals("Grupa invalida! \n", e.getMessage());
        }
    }

    // Test case for Valid ID, valid name, group number below lower boundary (e.g., -1)
    public void testSaveStudentGroupBelowLowerBoundary() {
        Student studentWithGroupBelowLowerBoundary = new Student("S001", "John Doe", -1);

        try {
            validationService.validate(studentWithGroupBelowLowerBoundary);
            fail("Expected ValidationException was not thrown for group below lower boundary");
        } catch (Exception e) {
            assertEquals("Grupa invalida! \n", e.getMessage());
        }
    }

    // Test case for Valid ID, valid name, group number above upper boundary (e.g., 1000)
    public void testSaveStudentGroupAboveUpperBoundary() {
        Student studentWithGroupAboveUpperBoundary = new Student("S001", "John Doe", 1000);

        try {
            validationService.validate(studentWithGroupAboveUpperBoundary);
            fail("Expected ValidationException was not thrown for group above upper boundary");
        } catch (Exception e) {
            assertEquals("Grupa invalida! \n", e.getMessage());
        }
    }


}
