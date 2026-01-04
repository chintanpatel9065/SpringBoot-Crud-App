package org.chintanpatel.springbootcrudapp.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentName("Computer Science");
        department = departmentRepository.save(department);
    }

    @Test
    void testSaveDepartment() {
        Department newDept = new Department();
        newDept.setDepartmentName("Mechanical Engineering");
        Department savedDept = departmentRepository.save(newDept);

        assertThat(savedDept).isNotNull();
        assertThat(savedDept.getDepartmentId()).isGreaterThan(0);
        assertThat(savedDept.getDepartmentName()).isEqualTo("Mechanical Engineering");
    }

    @Test
    void testFindAll() {
        List<Department> list = departmentRepository.findAll();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void testFindById() {
        Optional<Department> found = departmentRepository.findById(department.getDepartmentId());
        assertThat(found).isPresent();
        assertThat(found.get().getDepartmentName()).isEqualTo("Computer Science");
    }

    @Test
    void testExistsByDepartmentName() {
        boolean exists = departmentRepository.existsByDepartmentName("Computer Science");
        assertThat(exists).isTrue();

        boolean notExists = departmentRepository.existsByDepartmentName("Physics");
        assertThat(notExists).isFalse();
    }

    @Test
    void testDeleteDepartment() {
        departmentRepository.deleteById(department.getDepartmentId());
        Optional<Department> optional = departmentRepository.findById(department.getDepartmentId());
        assertThat(optional).isEmpty();
    }
}
