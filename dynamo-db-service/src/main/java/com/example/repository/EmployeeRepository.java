package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.entity.Employee;

@Repository
public class EmployeeRepository {

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	public EmployeeRepository(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}
	
	public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String userId) {
        return dynamoDBMapper.load(Employee.class, userId);
    }

    public String delete(String userId) {
        Employee emp = dynamoDBMapper.load(Employee.class, userId);
        dynamoDBMapper.delete(emp);
        return "Employee Deleted!";
    }

    public String update(String userId, Employee employee) {
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression()
        .withExpectedEntry("userId",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(userId)
                )));
        return userId;
    }
    
}
