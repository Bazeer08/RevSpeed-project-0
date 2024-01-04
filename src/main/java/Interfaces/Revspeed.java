package Interfaces;

import Model.Plan1;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Revspeed {
     int createAcc(User u) throws SQLException;
     int deleteAcc(int id) throws SQLException;
     int updateName(int id, String name) throws SQLException;

     int updateAddress(int id, String name) throws SQLException;

     int updatePassword(int id, String name) throws SQLException;
     ArrayList<Plan1> showAllPlan() throws SQLException;

     int addPlan(int planid,int userid) throws SQLException;

     Plan1 showMyPlan(int userid) throws SQLException;

     int changePlan(int userid) throws SQLException;

     int deletePlan(int userid) throws SQLException;

     User showMyDetails(int userid) throws SQLException;

}
