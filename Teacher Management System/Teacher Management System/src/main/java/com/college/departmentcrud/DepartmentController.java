package com.college.departmentcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody Department dept) {
        System.out.println("=== POST REQUEST ===");
        System.out.println("Adding department: " + dept.getName());
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO departments (name, location, budget) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dept.getName());
            ps.setString(2, dept.getLocation());
            ps.setDouble(3, dept.getBudget());
            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            return ResponseEntity.ok("Department added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        System.out.println("=== GET REQUEST ===");
        System.out.println("Fetching all departments...");
        List<Department> list = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM departments";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Department dept = new Department(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getDouble("budget")
                );
                list.add(dept);
                System.out.println("Found: " + dept.getName());
            }
            System.out.println("Total departments retrieved: " + list.size());
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.err.println("Error fetching departments:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(list);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable int id, @RequestBody Department dept) {
        System.out.println("=== PUT REQUEST ===");
        System.out.println("Updating department ID: " + id);
        try (Connection conn = getConnection()) {
            String sql = "UPDATE departments SET name=?, location=?, budget=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dept.getName());
            ps.setString(2, dept.getLocation());
            ps.setDouble(3, dept.getBudget());
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            System.out.println("Rows updated: " + rows);
            if (rows > 0) {
                return ResponseEntity.ok("Department updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Department with ID " + id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        System.out.println("=== DELETE REQUEST ===");
        System.out.println("Deleting department ID: " + id);
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM departments WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Rows deleted: " + rows);
            if (rows > 0) {
                return ResponseEntity.ok("Department deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Department with ID " + id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}