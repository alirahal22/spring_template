package com.alirahal.template.services;

import com.alirahal.template.database.repository.StudentRepository;
import com.alirahal.template.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        return StreamSupport.stream(studentIterable.spliterator(), false).collect(Collectors.toList());
    }

    public Student get(UUID id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student create(Student student) {
        Student newStudent = studentRepository.save(student);
        return newStudent;
    }

    public Student update(UUID id, Student updateData) {
        updateData.setId(id);
        studentRepository.save(updateData);
        Student updatedObject = studentRepository.findById(id).orElseThrow();
        return updatedObject;
    }

    public void delete(UUID id) {
        studentRepository.deleteById(id);
    }
}
