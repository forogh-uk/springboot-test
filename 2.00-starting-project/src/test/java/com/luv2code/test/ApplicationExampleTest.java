package com.luv2code.test;


import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {


    private static int count =0;


    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;


    @BeforeEach
    public void beforeEach(){
        count = count + 1;
        System.out.println(" Testing " + appInfo + " Which is " + appDescription +
                " Version: " + appVersion + " Execution of test method " + count );
        student.setFirstname("Forogh");
        student.setLastname("parvas");
        student.setEmailAddress("fp@hotmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,85.0,76.0,91.75)));
        student.setStudentGrades(studentGrades);


    }

    @Test
    void basicTest(){

    }

    @DisplayName("Add grade result for student grade")
    @Test
    public void addGradeResultsForStudentGrade(){
        assertEquals(352.75,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));


    }

    @DisplayName("Add grade result for Student grades not equals")
    @Test
    public void addGradeResultForStudentGradeAssertNotEquals(){
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("is grade grater")
    @Test
    public void isGraterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90,75),"failur ");
    }

    @DisplayName("is not grade grater")
    @Test
    public void isNotGraterStudentGrades(){
        assertFalse(studentGrades.isGradeGreater(70,75),"failur ");
    }

    @DisplayName("check null for student grade ")
    @Test
    public void checkNullStudentGrades(){
        assertNotNull(studentGrades.checkNull(studentGrades.getMathGradeResults()),"object should be not null");
    }


    @DisplayName("Create student without grade init")
    @Test

    public void createStudentWithoutGradeInit(){
        CollegeStudent studentTwo = context.getBean("collegeStudent" ,CollegeStudent.class);
        studentTwo.setFirstname("sayna");
        studentTwo.setLastname("khaz");
        studentTwo.setEmailAddress("sayna@hotmail.com");

        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }


    @DisplayName("Varify student are prototypes")
    @Test
    public void varifyStudentArePrototype(){
        CollegeStudent studentTwo = context.getBean("collegeStudent" ,CollegeStudent.class);

        assertNotSame(student,studentTwo);

    }

    @DisplayName("Find Grade point avarage")
    @Test
    public void findGradePointAvarage(){
        assertAll("Testing all assertEquals",
                ()-> assertEquals(352.75,studentGrades.addGradeResultsForSingleClass(
                        student.getStudentGrades().getMathGradeResults())),
                ()->assertEquals(88.19, studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()))
        );
    }






}
