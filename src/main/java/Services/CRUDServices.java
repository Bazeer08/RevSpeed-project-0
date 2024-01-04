package Services;

import DAO.CRUDDao;
import Interfaces.Revspeed;
import Model.Plan1;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDServices implements Revspeed {
    Revspeed cd;

    public CRUDServices() throws SQLException, ClassNotFoundException {
        cd = new CRUDDao();
    }
    public int createAcc(User u) throws SQLException {
        return cd.createAcc(u);
    }

    public int deleteAcc(int id) throws SQLException {
        return cd.deleteAcc(id);
    }

    public int updateName(int id, String name) throws SQLException {
        return cd.updateName(id,name);
    }

    public int updateAddress(int id, String name) throws SQLException {
        return cd.updateAddress(id,name);
    }

    public int updatePassword(int id, String name) throws SQLException {
        return cd.updatePassword(id,name);
    }

    public ArrayList<Plan1> showAllPlan() throws SQLException {
        return cd.showAllPlan();
    }

    public int addPlan(int planid,int userid) throws SQLException {
        return cd.addPlan(planid,userid);
    }

     public Plan1 showMyPlan(int userid) throws SQLException {
         return cd.showMyPlan(userid);
     }

     public int changePlan(int userid) throws SQLException {
         return cd.changePlan(userid);
     }

   public int deletePlan(int userid) throws SQLException {
       return cd.deletePlan(userid);
   }

   public User showMyDetails(int userid) throws SQLException
   {
       return cd.showMyDetails(userid);
   }


 }







