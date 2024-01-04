package Model;

public class User {
    private int uid;
    private String uname;
    private  long phnno;
    private String email;
    private String  address;


    private String pass;
    private int plan;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public int getPlan() {return plan;}

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public long getPhnno() {
        return phnno;
    }

    public void setPhnno(long phnno) {
        this.phnno = phnno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPass() {return pass;}

    public void setPass(String pass) {this.pass = pass;}
}
