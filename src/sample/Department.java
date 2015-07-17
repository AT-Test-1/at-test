package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


public class Department {

    private String title;

    private String codeDepartment;


    public Department() {
    }

    public Department(String title, String codeDepartment) {
        this.title = title;
        this.codeDepartment = codeDepartment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCodeDepartment() {
        return codeDepartment;
    }

    public void setCodeDepartment(String codeDepartment) {
        this.codeDepartment = codeDepartment;
    }

    public BasicDBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("title", title);
        document.put("codeDepartment", codeDepartment);

        return document;
    }

    public static Department fromDBObject(DBObject document) {
        Department object = new Department();

        object.title = (String) document.get("title");
        object.codeDepartment = (String) document.get("codeDepartment");

        return object;
    }

}
