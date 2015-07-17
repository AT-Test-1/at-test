package sample;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;



public class Service {

    public enum Category { ИП, ЮР, ФЛ }


    private String service_id;

    private String codeForm;

    private Category categoryGetter;

    List<Subservice> subservices;

    public Service() {
    }

    public Service(String service_id, String codeForm, Category categoryGetter, List<Subservice> subservices) {
        this.service_id = service_id;
        this.codeForm = codeForm;
        this.categoryGetter = categoryGetter;
        this.subservices = subservices;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getCodeForm() {
        return codeForm;
    }

    public void setCodeForm(String codeCompany) {
        codeForm = codeCompany;
    }

    public Category getCategoryGetter() {
        return categoryGetter;
    }

    public void setCategoryGetter(Category categoryGetter) {
        this.categoryGetter = categoryGetter;
    }

    public List<Subservice> getSubservices() {
        return subservices;
    }

    public void setSubservices(List<Subservice> subservices) {
        this.subservices = subservices;
    }

    public BasicDBObject toDBObject() {

        BasicDBObject document = new BasicDBObject();

        document.put("service_id", service_id);
        document.put("codeForm", codeForm);
        document.put("categoryGetter", categoryGetter);

        BasicDBList subservicesDBList = new BasicDBList();

        for(Subservice subservice : subservices) {
            subservicesDBList.add(subservice.toDBObject());
        }

        document.put("subservices", subservicesDBList);

        return document;
    }

    public static Service fromDBObject(DBObject document) {

        Service object = new Service();

        object.service_id = (String) document.get("service_id");
        object.codeForm = (String) document.get("codeForm");

        //categoryGetter
        String t = (String) document.get("categoryGetter").toString();
        if(t.equals("ИП")) {
            object.categoryGetter = Category.ИП;
        }
        if(t.equals("ЮР")) {
            object.categoryGetter = Category.ЮР;
        }
        if(t.equals("ФЛ")) {
            object.categoryGetter = Category.ФЛ;
        }

        //subservice
        object.subservices = new ArrayList<Subservice>();

        BasicDBList subservicesList = (BasicDBList) document.get("subservice");
        for(Object obj : subservicesList) {
            object.subservices.add( Subservice.fromDBObject((BasicDBObject)obj));
        }

        return object;
    }


}
