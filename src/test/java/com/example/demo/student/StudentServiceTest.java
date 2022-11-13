package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

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
    void willThrowWhenEmailIsTaken() {
        // Given
        Student student = new Student("Martín", "martin@gmail.com", Gender.MALE);

        // Le decimos que cuando encuentre el método selectExistsEmail() en el método addStudent del StudentService
        // que retorne true (para simular que existe el correo que se intentará registrar)
        given(this.studentRepository.selectExistsEmail(anyString()))
                .willReturn(true);

        // When
        // Then
        assertThatThrownBy(() -> this.underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        // De esta manera verificamos que, como este test trata de probar que se lanza una excepción
        // al tratar de registrar un email ya registrado, verificamos que en efecto, jamár
        // se llama al método save() del studentRepository()
        Mockito.verify(this.studentRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @Disabled
    void deleteStudent() {

    }
}