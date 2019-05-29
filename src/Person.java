import java.io.Serializable;

public class Person implements Serializable {
    private String name,cnic,phonenumber;

    public Person(String name, String cnic, String phonenumber) {
        this.name = name;
        this.cnic = cnic;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
