package sample;

import java.util.Date;


public class ReportItem {


    private int number;
    private Date dateOfMaking;
    private String codeForm;
    private Claim.Status status;
    private String fullName;
    private Person.Category typePerson;
    private String codeTarget;
    private String subservice;
    private String department;

    public ReportItem(int number, Claim claim, Subservice subservice) {
        this.number = number;
        this.dateOfMaking = claim.getDateOfMaking();
        this.codeForm = claim.getService().getCodeForm();
        this.status = claim.getStatus();
        this.fullName = claim.getPerson().getFullName();
        this.typePerson = claim.getPerson().getTypePerson();
        this.codeTarget = claim.getService().getService_id();
        this.subservice = subservice.getTitle();
        this.department = claim.getDepartment().getTitle();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfMaking() {
        return dateOfMaking;
    }

    public void setDateOfMaking(Date dateOfMaking) {
        this.dateOfMaking = dateOfMaking;
    }

    public String getCodeForm() {
        return codeForm;
    }

    public void setCodeForm(String codeForm) {
        this.codeForm = codeForm;
    }

    public Claim.Status getStatus() {
        return status;
    }

    public void setStatus(Claim.Status status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Person.Category getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(Person.Category typePerson) {
        this.typePerson = typePerson;
    }

    public String getCodeTarget() {
        return codeTarget;
    }

    public void setCodeTarget(String codeTarget) {
        this.codeTarget = codeTarget;
    }

    public String getSubservice() {
        return subservice;
    }

    public void setSubservice(String subservice) {
        this.subservice = subservice;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
