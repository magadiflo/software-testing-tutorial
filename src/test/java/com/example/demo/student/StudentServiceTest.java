package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new StudentService(this.studentRepository);
    }

    /**
     * canGetAllStudents(), lo que hacemos en esta prueba es verificar que
     * cuando se llame al método getAllStudents() de la clase de servicio,
     * internamente este llame al método findAll() del studentRepository,
     * de esta manera estaremos verificando su funcionamiento.
     */
    @Test
    void canGetAllStudents() {
        // When
        this.underTest.getAllStudents();

        // Then
        Mockito.verify(this.studentRepository).findAll();
    }

    @Test
    @Disabled
    void addStudent() {

    }

    @Test
    @Disabled
    void deleteStudent() {

    }
}