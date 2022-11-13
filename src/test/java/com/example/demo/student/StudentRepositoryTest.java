package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckIfStudentExistsEmail() {
        // Given
        String email = "martin@gmail.com";
        Student student = new Student("Martín", email, Gender.MALE);
        this.underTest.save(student);

        // When
        boolean exists = this.underTest.selectExistsEmail(email);

        // Then
        assertThat(exists).isTrue();
    }
}