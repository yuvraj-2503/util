package com.yuvraj.util;

import com.yuvraj.util.json.Json;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonTest {

    private Json json;

    @BeforeEach
    void setUp() {
        this.json = Json.create();
    }

    @AfterEach
    void tearDown() {
        this.json = null;
    }

    @Test
    void encode() {
        Employee e = new Employee(1, "Yuvraj", 23);
        String encoded = json.encode(e);
//        Employee decoded = json.decode(encoded, Employee.class);
//        assertEquals(e, decoded);
        System.out.println(encoded);
    }

    @Test
    void decode() {
        Employee e = new Employee(1, "Yuvraj", 23);
        String encoded = json.encode(e);
        Employee emp = json.decode(encoded, Employee.class);
        System.out.println(emp.toString());
    }

    @Test
    void convert() {
    }

    @Test
    void create() {
    }
}

class Employee {
    int id;
    String name;
    int age;

    public Employee() {

    }

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}