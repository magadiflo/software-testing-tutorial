package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tener en cuenta:
 * ****************
 * this.underTest.selectExistsEmail(email); que cuando hagamos pruebas, únicamente probaremos
 * los métodos que nosotros creemos, en este caso fue ese método personalizado. Ahora, como
 * en el StudentRepository estamos extendiendo de JpaRepository, tendremos muchos más métodos,
 * pero son métodos que ya vienen, y nosotros no debemos probar eso porque ya nos lor proporcionan
 * probados.
 * CONCLUSIÓN: Los únicos métodos que debemos probar son los métodos que agregamos a nuestra interfaz,
 * en nuestro caso el selectExistsEmail(email), con lo que probamos sus 2 escenarios: (1)si existe o
 * (2)no existe el email
 */

@DataJpaTest //Permitirá hacer las pruebas con conexión a la BD (h2) definida en el application.properties del dirctorio /test
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() { // Después de cada test, eliminaremos todos los Students de la BD
        this.underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // Given
        String email = "martin@gmail.com";
        Student student = new Student("Martín", email, Gender.MALE);
        this.underTest.save(student);

        // When
        boolean exists = this.underTest.selectExistsEmail(email);

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        // Given
        String email = "martin@gmail.com";

        // When
        boolean exists = this.underTest.selectExistsEmail(email);

        // Then
        assertThat(exists).isFalse();
    }
}