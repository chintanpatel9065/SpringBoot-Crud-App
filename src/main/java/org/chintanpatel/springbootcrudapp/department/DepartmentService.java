package org.chintanpatel.springbootcrudapp.department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department addDepartment(Department department);

    List<Department>getAllDepartmentList();

    Optional<Department>getDepartmentById(Long departmentId);

    void deleteDepartmentById(Long departmentId);

    boolean isDepartmentExist(String departmentName);
}
