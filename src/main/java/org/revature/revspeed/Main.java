package org.revature.revspeed;
import Interfaces.Revspeed;
import Model.Plan1;
import Model.User;
import Services.CRUDServices;
import Services.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            System.out.println("------------------WELCOME TO REVSPEED------------------------------");
            Scanner sc = new Scanner(System.in);
            Revspeed cs = new CRUDServices();
            Login l = new Login();
            logger.info("Application Started {}", Main.class.getSimpleName());

            System.out.println("Select Option and press Enter" +
                    "\n1. Login" +
                    "\n2. Create New Account" +
                    "\n3. Exit");

            int mainOpt = sc.nextInt();
            switch (mainOpt) {
                case 1: {
                    System.out.println("Enter Your Registered Email");
                    String email = sc.next();
                    System.out.println("Enter Your Password");
                    String pass = sc.next();

                    try {
                        User current = l.login(email, pass);

                        if (current != null) {
                            System.out.println("Welcome >> " + current.getUname() + " <<");

                            loggedIn: while (true) {
                                System.out.println("Press" +
                                        "\n1. Add Plan" +
                                        "\n2. Show My Plan" +
                                        "\n3. Change My Plan" +
                                        "\n4. Abort My Plan" +
                                        "\n5. Show My Details" +
                                        "\n6. Update My Details" +
                                        "\n7. Delete My Account" +
                                        "\n8. Exit or Any key to exit");

                                try {
                                    int choice = sc.nextInt();

                                    switch (choice) {
                                        case 1: {
                                            // Add plan
                                            ArrayList<Plan1> obj = cs.showAllPlan();
                                            System.out.println("Plan_no"
                                                    + "\t plan_name"
                                                    + "\t plan_price"
                                                    + "\t plan_duration"
                                                    + "\t plan_data & Speed");
                                            for (Plan1 p : obj) {
                                                System.out.println(p.getPid()
                                                        + "\t      |" + p.getPname()
                                                        + "\t      |" + p.getPprice()
                                                        + "\t      |" + p.getPduration()
                                                        + "\t      |" + p.getPdata_speed());
                                            }
                                            System.out.println("Press a Plan_no to add plan");
                                            int planChoice = sc.nextInt();
                                            int cur = cs.addPlan(planChoice, current.getUid());
                                            if (cur == 1) {
                                                System.out.println("Plan added Successfully. Enjoy Your Subscription.");
                                            } else if (cur == 0) {
                                                System.out.println("No such Plan Exists. Please try again.");
                                            } else {
                                                System.out.println("You already have a plan. Please abort the current plan and try adding again.");
                                            }
                                            continue loggedIn;
                                        }
                                        case 2: {
                                            // Show My Plan
                                            Plan1 p = cs.showMyPlan(current.getUid());
                                            if (p.getPid() != 0) {
                                                System.out.println("Plan_no" +
                                                        "\t plan_name" +
                                                        "\t plan_price" +
                                                        "\t plan_duration" +
                                                        "\t plan_data & speed");
                                                System.out.println(p.getPid() +
                                                        "\t    " + p.getPname() +
                                                        "\t     " + p.getPprice() +
                                                        "\t      " + p.getPduration() +
                                                        "\t       " + p.getPdata_speed());
                                            } else {
                                                System.out.println("No Active plans");
                                            }
                                            continue loggedIn;
                                        }
                                        case 3: {
                                            // Change My Plan
                                            int cur = cs.changePlan(current.getUid());
                                            if (cur == 0) {
                                                System.out.println("You have No Active Plan Right Now. Try after Adding plan");

                                            } else {
                                                System.out.println("You have a current active plan. Please abort your Current plan and Change your plan");
                                                System.out.println("Did You Wish to abort your plan\n1.yes\n2.no");
                                                int term=sc.nextInt();
                                                if(term==1)
                                                {
                                                    int curr = cs.deletePlan(current.getUid());
                                                    if(curr==0)
                                                    {
                                                        System.out.println("You have no current plan to Deactivate");
                                                    }
                                                    else {
                                                        System.out.println("Plan Successfully Deactivated");
                                                    }
                                                }

                                            }
                                            continue loggedIn;
                                        }
                                        case 4: {
                                            // Abort My Plan
                                            System.out.println("Are you Sure to Abort the Plan press\n1.Yes\n2. No");
                                            int confirm = sc.nextInt();
                                            if (confirm == 1) {
                                                int cur = cs.deletePlan(current.getUid());
                                                if (cur == 0) {
                                                    System.out.println("You have no current plan to Deactivate");
                                                } else {
                                                    System.out.println("Plan Successfully Deactivated");
                                                }
                                            } else if (confirm == 2) {
                                                System.out.println("Deactivation process aborted");
                                            }
                                            continue loggedIn;
                                        }
                                        case 5: {
                                            // Show My Details
                                            User cur=cs.showMyDetails(current.getUid());
                                            System.out.println("ID"
                                                    + "\tName"
                                                    + "\tPhone Number"
                                                    + "\tEmail"
                                                    + "\tAddress"
                                                    + "\tPassword"
                                            );
                                            System.out.println(cur.getUid()
                                                    + "\t" + cur.getUname()
                                                    + "\t" + cur.getPhnno()
                                                    + "\t" + cur.getEmail()
                                                    + "\t" + cur.getAddress()
                                                    + "\t" + cur.getPass());

                                            continue loggedIn;
                                        }
                                        case 6: {
                                            // Update My Details
                                            System.out.println("Press 1 to update name");
                                            System.out.println("Press 2 to update address");
                                            System.out.println("Press 3 to update password");
                                            int uChoice = sc.nextInt();
                                            if (uChoice == 1) {
                                                System.out.println("Enter the name to be updated");
                                                String upName = sc.next();
                                                int status = cs.updateName(current.getUid(), upName);
                                                if (status == 1) {
                                                    System.out.println("Updated successfully");
                                                } else {
                                                    System.out.println("Updation failed");
                                                }
                                            } else if (uChoice == 2) {
                                                System.out.println("Enter the address to be updated");
                                                String upAddress = sc.next();
                                                int status = cs.updateAddress(current.getUid(), upAddress);
                                                if (status == 1) {
                                                    System.out.println("Updated successfully");
                                                } else {
                                                    System.out.println("Updation failed");
                                                }
                                            } else if (uChoice == 3) {
                                                System.out.println("Enter the Password to be updated");
                                                String upPassword = sc.next();
                                                int status = cs.updatePassword(current.getUid(), upPassword);
                                                if (status == 1) {
                                                    System.out.println("Updated successfully");
                                                } else {
                                                    System.out.println("Updation failed");
                                                }
                                            } else {
                                                System.out.println("Invalid entry");
                                            }
                                            continue loggedIn;
                                        }
                                        case 7: {
                                            // Delete My Account
                                            System.out.println("Press 1 to confirm delete");
                                            int check = sc.nextInt();
                                            if (check == 1) {
                                                int status = cs.deleteAcc(current.getUid());
                                                if (status == 1) {
                                                    System.out.println("Account Deleted Successfully");
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("Account deletion failed");
                                                }
                                            } else {
                                                System.out.println("Account deletion Failed");
                                                continue loggedIn;
                                            }
                                            break;
                                        }
                                        case 8:
                                            System.exit(0);
                                        default:
                                            System.out.println("Invalid Choice. Please try again.");
                                            continue loggedIn;
                                    }
                                } catch (InputMismatchException e) {
                                    logger.error("InputMismatchException occurred", e);
                                    System.out.println("Invalid Entry. Please enter a valid integer.");
                                    sc.nextLine(); // Consume the remaining input
                                    continue loggedIn;
                                }
                            }
                        } else {
                            System.out.println("User Invalid Login. Login Failed.");
                            main(args);
                        }
                    } catch (NullPointerException e) {
                        logger.error("NullPointerException occurred", e);
                        System.out.println("User doesn't exist.");
                        main(args);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter your user name:");
                    String uName = sc.next();
                    System.out.println("Enter your user email:");
                    String uEmail = sc.next();
                    System.out.println("Enter your user address:");
                    String uAddress = sc.next();
                    System.out.println("Enter your user phone number:");
                    long uPhoneNo = sc.nextLong();
                    System.out.println("Enter your user password:");
                    String uPassword = sc.next();
                    System.out.println("Re-enter your password:");
                    String rePassword = sc.next();

                    while (!uPassword.equals(rePassword)) {
                        System.out.println("Passwords do not match. Re-enter your password:");
                        uPassword = sc.next();
                        System.out.println("Re-enter your password:");
                        rePassword = sc.next();
                    }

                    User u = new User();
                    u.setUname(uName);
                    u.setPhnno(uPhoneNo);
                    u.setAddress(uAddress);
                    u.setEmail(uEmail);
                    u.setPass(uPassword);

                    int status = cs.createAcc(u);
                    if (status != 0) {
                        System.out.println("Account created successfully");
                        System.out.println("Press 1 to go back to the login page");
                        int choice = sc.nextInt();
                        if (choice == 1) {
                            main(args);
                        } else {
                            System.out.println("Please enter a valid entry");
                            main(args);
                        }
                    } else {
                        System.out.println("Account already exists or try again later");
                        main(args);
                    }
                    break;
                }
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice. Please enter a valid option.");
                    main(args);
            }

        } catch (SQLException ex) {
            logger.error("SQLException occurred", ex);
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            logger.error("ClassNotFoundException occurred", ex);
            throw new RuntimeException(ex);
        } catch (InputMismatchException ex) {
            logger.error("InputMismatchException occurred", ex);
            System.out.println("Please enter a valid entry.");
            main(args);
        } catch (Exception e) {
            logger.error("Unexpected exception occurred", e);
            e.printStackTrace();
        }
    }
}
