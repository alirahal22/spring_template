package com.alirahal.template.services;

import com.alirahal.template.database.repository.StudentRepository;
import com.alirahal.template.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentService extends RestService<Student, StudentRepository> {

}
