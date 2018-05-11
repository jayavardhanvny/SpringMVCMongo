package org.letuslearn.service;

import java.util.List;
import java.util.UUID;


import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.letuslearn.model.mongo.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Service for processing {@link Employee} objects.
 * Uses Spring's {@link MongoTemplate} to perform CRUD operations.
 * * For a complete reference to MongoDB
 * see http://www.mongodb.org/
 *

 * For a complete reference to Spring Data MongoDB
 * see http://www.springsource.org/spring-data
 *
 * @author Dinesh Rajput
 *
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired(required=true)
    private MongoTemplate mongoTemplate;
//
// @Autowired
// private MongoOperations mongoOperations;

    protected static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    /**
     * Retrieves all Employees
     */
    public List<Employee> listEmployeess() {
        logger.debug("Retrieving all Employees");

        // Execute the query and find all matching entries
        List<Employee> employees = mongoTemplate.findAll(Employee.class);

        return employees;
    }

    /**
     * Retrieves a single Employee
     */
    public Employee getEmployee(String empid ) {
        logger.debug("Retrieving an existing Employee");
        Employee employee = new Employee();

        // Find an entry where empid matches the id
        //BsonDocument bsonDocument = new BsonDocument();
        DBObject query = new BasicDBObject();
        query.put("empId", empid);
        System.out.println("empid "+empid);
        //mongoTemplate.getDb().getCollection("employee").find(new BsonDocument());
        //DBObject cursor = mongoTemplate.getDb().
                //getCollection("employee").find(query);
        //Query queryEmployeeCollection = new Query();
        //queryEmployeeCollection.addCriteria(Criteria.where("empId").is(empid));
        //Bson filters = Filters.eq("empId", empid);
        //BsonDocument bsonDocument = new BsonDocument();
        //bsonDocument.put("empId", new BsonString(empid));
       Document document = mongoTemplate.getDb().
                getCollection("employee").find(Filters.eq("empId", empid)).first();
        System.out.println("document "+document);
        employee.setEmpId(document.get("empId").toString());
        employee.setEmpName(document.get("empName").toString());
        employee.setEmpAge(document.get("empAge").toString());
        employee.setSalary(document.get("salary").toString());
        employee.setEmpAddress(document.get("empAddress").toString());

        return employee;
    }

    /**
     * Adds a new Employee
     */
    public Boolean addEmployee(Employee employee) {
        //System.out.println("Adding a new employee");

        try {
            //employee.setEmpId(UUID.randomUUID().toString());

            if(employee.getEmpId() != null && employee.getEmpId() != ""){
                // Find an entry where empid matches the id
                //System.out.println("Log 1 ");
                DBObject query1 = new BasicDBObject();
                //System.out.println("Log 4 ");
                query1.put("empId", employee.getEmpId());
                //System.out.println("Log 5 ");
                DBObject query = new BasicDBObject();
                query.put("empId", employee.getEmpId());
                query.put("empName", employee.getEmpName());
                query.put("empAge", employee.getEmpAge());
                query.put("salary", employee.getSalary());
                query.put("empAddress", employee.getEmpAddress());
                //System.out.println("Log 2 ");
                Document document = new Document("empId", employee.getEmpId()).append("empName", employee.getEmpName())
                        .append("empAge", employee.getEmpAge()).append("salary", employee.getSalary())
                        .append("empAddress", employee.getEmpAddress());
                //System.out.println("Log 3 ");

                mongoTemplate.getDb().getCollection("employee").insertOne(document);

                System.out.println("After insert ");
                //mongoTemplate.getDb().getCollection("employee").withDocumentClass(Employee.class).insertOne(employee);
                //mongoTemplate.getDb().getCollection("employee").update(query1, query);
            }else{
                // Set a new value to the empid property first since it's blank
                employee.setEmpId(UUID.randomUUID().toString());
                // Insert to db
                mongoTemplate.save(employee);
                System.out.println("Log 3 ");
            }

            return true;
        }
        catch (Exception e) {
            System.out.println("An error has occurred while trying to add new employee");
            return false;
        }
    }

    /**
     * Deletes an existing employee
     */
    public Boolean deleteEmployee(String empid) {
        logger.debug("Deleting existing employee");

        try {

            // Find an entry where empid matches the id
            DBObject query = new BasicDBObject();
            query.put("empId", empid);
            // Run the query and delete the entry
            mongoTemplate.getDb().getCollection("employee").deleteOne(Filters.eq(query));
            //mongoTemplate.getDb().getCollection("employee").findAndRemove(query);

            return true;
        } catch (Exception e) {
            logger.error("An error has occurred while trying to delete new employee", e);
            return false;
        }
    }
}