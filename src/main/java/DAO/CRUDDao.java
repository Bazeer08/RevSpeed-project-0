package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import DataBase.Databaseconnection;
import Interfaces.Revspeed;
import Model.Plan1;
import Model.User;

public class CRUDDao implements Revspeed {
    public Connection con;

    public CRUDDao() throws SQLException, ClassNotFoundException {

        con=Databaseconnection.access().getCon();
    }




    //Used for Login process
    public User login(String email,String pass) throws SQLException
    {

        String q="select * from udetails where email=?" ;
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1,email);
        ResultSet rs=ps.executeQuery();
        User p=new User();
        while(rs.next())
        {

            p.setUid(rs.getInt(1));
            p.setUname(rs.getString(2));
            p.setPhnno(rs.getLong(3));
            p.setEmail(rs.getString(4));
            p.setAddress(rs.getString(5));
            p.setPass(rs.getString(6));

        }
        if(p.getEmail().equals(email) && p.getPass().equals(pass)) {
            return p;
        }
        else {
            return null;
        }
    }

    //Create a particular user for registratio process
    public int createAcc(User u) throws SQLException {
        String q="insert into Udetails(uname,uphnno,email,address,pass) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1,u.getUname());
        ps.setLong(2,u.getPhnno());
        ps.setString(3,u.getEmail());
        ps.setString(4,u.getAddress());
        ps.setString(5,u.getPass());
        return ps.executeUpdate();
    }


    //Delete a particular user who is inside login
    public int deleteAcc(int id) throws SQLException {
        String q="delete from udetails where uid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1,id);
        return ps.executeUpdate();
    }

    //update name of logined user
    public int updateName(int id ,String upname) throws SQLException {
        String q="update udetails set uname=? where uid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1,upname);
        ps.setInt(2,id);
        return ps.executeUpdate();

    }

    //update an address a particular logined user
    public int updateAddress(int id ,String upaddress) throws SQLException {
        String q="update udetails set address=? where uid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1,upaddress);
        ps.setInt(2,id);
        return ps.executeUpdate();

    }

    //update a password a particular logined user
    public int updatePassword(int id ,String upPassword) throws SQLException {
        String q="update udetails set pass=? where uid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1,upPassword);
        ps.setInt(2,id);
        return ps.executeUpdate();

    }

    public ArrayList<Plan1> showAllPlan() throws SQLException {
        ArrayList<Plan1> obj= new ArrayList<>();
        String q="select * from service1";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet rs=ps.executeQuery();

        while(rs.next())
        {
            Plan1 p=new Plan1();
            p.setPid(rs.getInt(1));
            p.setPname(rs.getString(2));
            p.setPprice(rs.getDouble(3));
            p.setPduration(rs.getString(4));
            p.setPdata_speed(rs.getString(5));
            obj.add(p);
        }


        return obj;
    }

    public int addPlan(int planid,int userid) throws SQLException {
        HashSet<Integer> hs=new HashSet<>();
        String q="select plan from udetails where uid=?";
        String q2="select pid from service1";
        PreparedStatement ps2= con.prepareStatement(q2);
        ResultSet rs2=ps2.executeQuery();
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1,userid);
        ResultSet rs=ps.executeQuery();
        User u=new User();
        while(rs.next())
        {
            u.setPlan(rs.getInt(1));
        }

        while(rs2.next())
        {
            hs.add(rs2.getInt(1));
        }
        int checked=0;
        if(u.getPlan()==0)
        {
            for(Integer check:hs)
            {
                if(planid==check)
                {
                    String uq="update udetails set plan=? where uid=?";
                    PreparedStatement ps1=con.prepareStatement(uq);
                    ps1.setInt(1,planid);
                    ps1.setInt(2,userid);
                    checked=ps1.executeUpdate();
                }
            }
            return checked;
        }
        else {
            return -1;
        }


    }

    public Plan1 showMyPlan(int userid) throws SQLException {

        String q="select * from service1 where pid in(select plan from udetails where uid=?)";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1,userid);
        ResultSet rs=ps.executeQuery();
        Plan1 p=new Plan1();
        while(rs.next())
        {
            p.setPid(rs.getInt(1));
            p.setPname(rs.getString(2));
            p.setPprice(rs.getDouble(3));
            p.setPduration(rs.getString(4));
            p.setPdata_speed(rs.getString(5));
        }
        return p;

    }

    public int changePlan(int userid) throws SQLException {
        String q="select plan from udetails where uid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1,userid);
        ResultSet rs=ps.executeQuery();
        User u=new User();
        while(rs.next())
        {
            u.setPlan(rs.getInt(1));
        }
        return u.getPlan();
    }

    public int deletePlan(int userid) throws SQLException {
        String q="select plan from udetails where uid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1,userid);
        ResultSet rs=ps.executeQuery();
        User u=new User();
        while(rs.next()) {
            u.setPlan(rs.getInt(1));
        }

        if(u.getPlan()==0)
        {
            return u.getPlan();
        }
        else {
            String dq = "update udetails set plan=NULL where uid=?";
            PreparedStatement ps1 = con.prepareStatement(dq);
            ps1.setInt(1, userid);

            return ps1.executeUpdate();
        }

    }

    public User showMyDetails(int userid) throws SQLException
    {
        String q="select * from udetails where uid=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, userid);
        ResultSet rs=ps.executeQuery();
        User u=new User();
        while(rs.next())
        {
            u.setUid(rs.getInt(1));
            u.setUname(rs.getString(2));
            u.setPhnno(rs.getLong(3));
            u.setEmail(rs.getString(4));
            u.setAddress(rs.getString(5));
            u.setPass(rs.getString(6));
            u.setPlan(rs.getInt(7));
        }
        return u;

    }



}