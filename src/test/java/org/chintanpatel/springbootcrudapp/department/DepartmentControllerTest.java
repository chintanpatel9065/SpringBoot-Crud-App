package org.chintanpatel.springbootcrudapp.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
class DepartmentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockitoBean
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testListDepartments() throws Exception {
        Department dept = new Department();
        dept.setDepartmentName("Computer Science");
        when(departmentService.getAllDepartmentList()).thenReturn(List.of(dept));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(view().name("department/department-list"))
                .andExpect(model().attributeExists("departments"));
    }

    @Test
    void testCreateDepartmentForm() throws Exception {
        mockMvc.perform(get("/departments/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("department/department-form"))
                .andExpect(model().attributeExists("department"));
    }

    @Test
    void testInsertDepartmentSuccess() throws Exception {
        when(departmentService.isDepartmentExist(anyString())).thenReturn(false);
        when(departmentService.addDepartment(any(Department.class))).thenReturn(new Department());

        mockMvc.perform(post("/departments/insertOrUpdateDepartment")
                        .param("departmentName", "Physics"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"))
                .andExpect(flash().attribute("successMessage", "Department added successfully"));
    }

    @Test
    void testInsertDepartmentExists() throws Exception {
        when(departmentService.isDepartmentExist(anyString())).thenReturn(true);

        mockMvc.perform(post("/departments/insertOrUpdateDepartment")
                        .param("departmentName", "Computer Science"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments/new"))
                .andExpect(flash().attribute("errorMessage", "Department already exists"));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Computer Science Updated");

        mockMvc.perform(post("/departments/insertOrUpdateDepartment")
                        .param("departmentId", "1")
                        .param("departmentName", "Computer Science Updated"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"))
                .andExpect(flash().attribute("successMessage", "Department updated successfully"));
    }

    @Test
    void testGetDepartment() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Computer Science");
        when(departmentService.getDepartmentById(1L)).thenReturn(Optional.of(dept));

        mockMvc.perform(get("/departments/getDepartment/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("department/department-form"))
                .andExpect(model().attributeExists("department"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        mockMvc.perform(get("/departments/deleteDepartment/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"))
                .andExpect(flash().attribute("successMessage", "Department deleted successfully"));
    }
}
