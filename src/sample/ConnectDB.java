package sample;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static sample.Person.Category.*;


public class ConnectDB {

    public DBCollection subserviceCollection;
    public DBCollection claimCollection;
    public DBCollection serviceCollection;
    public DBCollection personCollection;
    public DBCollection departmentCollection;

    public ConnectDB() throws Exception {

        Mongo m = new Mongo("127.0.0.1", 27017);
        DB db = m.getDB( "AT-Test14" );

        subserviceCollection = db.getCollection("Subservice");
        claimCollection = db.getCollection("Claim");
        serviceCollection = db.getCollection("Service");
        personCollection = db.getCollection("Person");
        departmentCollection = db.getCollection("Department");


        DBCursor cursor = claimCollection.find();
        if (cursor.hasNext()) {

        }
        else
        {
            intitDB();
            intitDB();
        }

    }


    public List<Claim> getClaims() {
        List<Claim> claims = new ArrayList<Claim>();

        DBCursor cursor = claimCollection.find();
        while (cursor.hasNext()) {
            DBObject dbo = cursor.next();

            claims.add(Claim.fromDBObject(dbo, this));
        }
        return claims;
    }



    public void intitDB() throws Exception {

        List<BasicDBObject> list = new ArrayList<BasicDBObject>();
        list.add(new BasicDBObject("codeTarget","11").append("title", "title1"));
        list.add(new BasicDBObject("codeTarget", "22").append("title", "title2"));

        BasicDBObject service = new BasicDBObject("service_id", "service001")
                .append("codeForm", "codeForm1")
                .append("categoryGetter", ИП.toString())
                .append("subservice", list);


        serviceCollection.insert(service);




        BasicDBObject person = new BasicDBObject("person_id", "person001")
                .append("fullName", "name1 lastName1")
                .append("typePerson", ИП.toString());
        personCollection.insert(person);



        BasicDBObject department = new BasicDBObject("department_id", "department001")
                .append("title", "department1")
                .append("codeDepartment", "codeDepartment1");
        departmentCollection.insert(department);



        BasicDBObject doc = new BasicDBObject("claim_id", "claim001")
                .append("service_id", "service001")
                .append("person_id", "person001")
                .append("date", new Date().toString())
                .append("status", Main.Status.s1.toString())
                .append("department_id", "department001");
        claimCollection.insert(doc);


//////////////////////////////////////////////////////////////////////////////////

        List<BasicDBObject> list2 = new ArrayList<BasicDBObject>();
        list2.add(new BasicDBObject("codeTarget","2.11").append("title", "title2.1"));
        list2.add(new BasicDBObject("codeTarget", "2.22").append("title", "title2.2"));

        BasicDBObject service2 = new BasicDBObject("service_id", "service002")
                .append("codeForm", "codeForm2")
                .append("categoryGetter", ИП.toString())
                .append("subservice", list2);


        serviceCollection.insert(service2);




        BasicDBObject person2 = new BasicDBObject("person_id", "person002")
                .append("fullName", "name2 lastName2")
                .append("typePerson", ИП.toString());
        personCollection.insert(person2);



        BasicDBObject department2 = new BasicDBObject("department_id", "department002")
                .append("title", "department2")
                .append("codeDepartment", "codeDepartment2");
        departmentCollection.insert(department2);



        BasicDBObject doc2 = new BasicDBObject("claim_id", "claim002")
                .append("service_id", "service002")
                .append("person_id", "person002")
                .append("date", new Date().toString())
                .append("status", Main.Status.s1.toString())
                .append("department_id", "department002");
        claimCollection.insert(doc2);

        //////////////////////////////////////////////////////////////////////

        List<BasicDBObject> list4 = new ArrayList<BasicDBObject>();
        list4.add(new BasicDBObject("codeTarget","4.11").append("title", "title4.1"));
        list4.add(new BasicDBObject("codeTarget", "4.22").append("title", "title4.2"));

        BasicDBObject service4 = new BasicDBObject("service_id", "service004")
                .append("codeForm", "codeForm4")
                .append("categoryGetter", ИП.toString())
                .append("subservice", list4);


        serviceCollection.insert(service4);




        BasicDBObject person4 = new BasicDBObject("person_id", "person004")
                .append("fullName", "name4 lastName4")
                .append("typePerson", ИП.toString());
        personCollection.insert(person4);



        BasicDBObject department4 = new BasicDBObject("department_id", "department004")
                .append("title", "department4")
                .append("codeDepartment", "codeDepartment4");
        departmentCollection.insert(department4);


        BasicDBObject doc4 = new BasicDBObject("claim_id", "claim004")
                .append("service_id", "service004")
                .append("person_id", "person004")
                .append("date", new Date(2015,04,14).toString())
                .append("status", Main.Status.s1.toString())
                .append("department_id", "department004");
        claimCollection.insert(doc4);



        System.out.println(claimCollection.findOne());


        DBCursor cur = claimCollection.find();
        while(cur.hasNext()) {
            System.out.println(cur.next());
        }

    }




}
