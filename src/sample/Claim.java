package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Claim {

    public enum Status { S1, S2, S4 }

    private String claim_id;

    private Service service;

    private Person person;

    private Date dateOfMaking;

    private Status status;

    private Department department;


    public Claim() {
    }

    public Claim(String claim_id, Service service, Person person, Date dateOfMaking, Status status, Department department) {
        this.claim_id = claim_id;
        this.service = service;
        this.person = person;
        this.dateOfMaking = dateOfMaking;
        this.status = status;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDateOfMaking() {
        return dateOfMaking;
    }

    public void setDateOfMaking(Date dateOfMaking) {
        this.dateOfMaking = dateOfMaking;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BasicDBObject toDBObject() {

        BasicDBObject document = new BasicDBObject();

        document.put("codeClaim", claim_id);
        document.put("service", service.getCodeForm());
        document.put("person", person.getFullName());
        document.put("dateOfMaking", dateOfMaking.toString());
        document.put("status", status.toString());
        document.put("department", department.getCodeDepartment());

        return document;

    }

    public static Claim fromDBObject(DBObject document, ConnectDB connectDB) {

        Claim object = new Claim();

        object.claim_id = (String) document.get("claim_id");

        //Service
        BasicDBObject queryService = new BasicDBObject();
        queryService.put("service_id", document.get("service_id"));
        DBCursor curService = connectDB.serviceCollection.find(queryService);
        if(curService.hasNext()) {
            DBObject serviceDB = curService.next();
            Service ser = Service.fromDBObject(serviceDB);
            object.service = ser;
        }

        //Person
        BasicDBObject query = new BasicDBObject();
        query.put("person_id", document.get("person_id"));
        DBCursor cur = connectDB.personCollection.find(query);
        if(cur.hasNext()) {
            DBObject personDB = cur.next();
            Person p = Person.fromDBObject(personDB);
            object.person = p;
        }

        //Date
        try
        {
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            Date date = format.parse((String) document.get("date"));
            object.dateOfMaking = date;
        }
        catch (ParseException e) {
            System.out.println("Claim parse date");
        }

        //Status
        String t = (String) document.get("status");

        if(t.equals("s1"))
        {
            object.status = Status.S1;
        }
        if(t.equals("s2"))
        {
            object.status = Status.S2;
        }
        if(t.equals("s4"))
        {
            object.status = Status.S4;
        }

        //Department
        BasicDBObject queryDepartment = new BasicDBObject();
        queryDepartment.put("department_id", document.get("department_id"));
        DBCursor curDepartment = connectDB.departmentCollection.find(queryDepartment);
        if(curDepartment.hasNext()) {
            DBObject departmentDB = curDepartment.next();
            Department p = Department.fromDBObject(departmentDB);
            object.department = p;
        }

        return object;
    }


}
