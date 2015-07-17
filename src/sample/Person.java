package sample;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Person {

    public enum Category { ИП, ЮЛ, ФЛ }

    private String fullName;

    private Category typePerson;

    public Person() {
    }

    public Person(String fullName, Category typePerson) {
        this.fullName = fullName;
        this.typePerson = typePerson;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Category getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(Category typePerson) {
        this.typePerson = typePerson;
    }

    public BasicDBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("fullName", fullName);
        document.put("typePerson", fullName);

        return document;
    }

    public static Person fromDBObject(DBObject document) {
        Person object = new Person();

        object.fullName = (String) document.get("fullName");

        String t = (String) document.get("typePerson");
        if(t.equals("ИП"))
        {
            object.typePerson = Category.ИП;
        }
        if(t.equals("ЮЛ"))
        {
            object.typePerson = Category.ЮЛ;
        }
        if(t.equals("ФЛ"))
        {
            object.typePerson = Category.ФЛ;
        }

        return object;
    }


}
