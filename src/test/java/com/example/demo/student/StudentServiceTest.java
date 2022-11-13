package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    void canAddStudent() {
        // Given
        Student student = new Student("Martín", "martin@gmail.com", Gender.MALE);

        // When
        this.underTest.addStudent(student);

        // Then
        // Con el ArgumentCaptor de Mockito, capturamos el argumento que llega justo dentro del
        // método studentRepository.save(...), esto con la finalidad de comparar, es decir,
        // tanto el valor capturado como el student definido al inicio de este test, deben
        // ser iguales, ya que supuestamente es lo que se envió al método addStudent() de la
        // clase StudentService
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(this.studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    @Disabled
    void deleteStudent() {

    }
}