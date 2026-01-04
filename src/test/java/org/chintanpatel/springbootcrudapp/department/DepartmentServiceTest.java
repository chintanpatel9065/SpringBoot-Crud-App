package org.chintanpatel.springbootcrudapp.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Computer Science");
    }

    @Test
    void testSaveDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department savedDept = departmentService.addDepartment(new Department());

        assertThat(savedDept).isNotNull();
        assertThat(savedDept.getDepartmentName()).isEqualTo("Computer Science");
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void testGetAllDepartments() {
        when(departmentRepository.findAll()).thenReturn(List.of(department));

        List<Department> list = departmentService.getAllDepartmentList();

        assertThat(list).isNotEmpty();
        assertThat(list.get(0).getDepartmentName()).isEqualTo("Computer Science");
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> found = departmentService.getDepartmentById(1L);

        assertThat(found).isPresent();
        assertThat(found.get().getDepartmentName()).isEqualTo("Computer Science");
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(departmentRepository).deleteById(1L);

        departmentService.deleteDepartmentById(1L);

        verify(departmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testIsDepartmentExist() {
        when(departmentRepository.existsByDepartmentName("Computer Science")).thenReturn(true);

        boolean exists = departmentService.isDepartmentExist("Computer Science");

        assertThat(exists).isTrue();
        verify(departmentRepository, times(1)).existsByDepartmentName("Computer Science");
    }
}
