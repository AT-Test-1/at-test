package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


public class Subservice {

    private String codeTarget;

    private String title;

    public Subservice(String codeTarget, String title) {
        this.codeTarget = codeTarget;
        this.title = title;
    }

    public Subservice() {
    }

    public String getCodeTarget() {
        return codeTarget;
    }

    public void setCodeTarget(String codeTarget) {
        this.codeTarget = codeTarget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BasicDBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("codeTarget", codeTarget);
        document.put("title", title);

        return document;
    }

    public static Subservice fromDBObject(DBObject document) {
        Subservice object = new Subservice();

        object.codeTarget = (String) document.get("codeTarget");
        object.title = (String) document.get("title");

        return object;
    }

    @Override
    public String toString() {
        return "Subservice{" +
                "codeTarget='" + codeTarget + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
