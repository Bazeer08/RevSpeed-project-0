package Services;

import DAO.CRUDDao;
import Model.User;

import java.sql.SQLException;

public class Login {
    CRUDDao cd = null;

    public Login() throws SQLException, ClassNotFoundException {
        cd = new CRUDDao();
    }


    public User login(String email, String pass) throws SQLException, ClassNotFoundException {
        System.out.println("Fetching the data...........");
        User status = cd.login(email, pass);
        return status;

    }
}
