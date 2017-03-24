/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sailorinfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class for connecting to db
 *
 * @author Meeth
 */
public class JDBCConnect {

    private Connection con = null;

    /**
     *
     * @throws IOException
     */
    public JDBCConnect() throws IOException {
        String url = "jdbc:derby://localhost:1527/EmployeeDB";
        String username = "meeth";
        String password = "meeth";
        //ReadFiles r1= new ReadFiles();
        //r1.readFile("SailorData.txt");

        try {
            con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    /**
     * generates report 3
     *
     * @throws IOException
     * @throws SQLException
     */
    public void report3() throws IOException, SQLException {

        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ReadFiles r1 = new ReadFiles();
            r1.readFile("SailorData.txt");
            String delete = "DELETE FROM SAILOR";
            String query = " SELECT * FROM SAILOR";
            stmt.execute(delete);
            PreparedStatement pStmt = con.prepareStatement("insert into Sailor values (?,?,?,?)");
            //int i=1;
            for (int i = 1; i <= 100; i++) {
                pStmt.setString(1, r1.getColumns().get(i)[0]);
                pStmt.setString(2, r1.getColumns().get(i)[1]);
                pStmt.setString(3, r1.getColumns().get(i)[2]);
                pStmt.setInt(4, Integer.parseInt(r1.getColumns().get(i)[3]));
                pStmt.executeUpdate();
            }

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String lname = rs.getString("LASTNAME");
                String fname = rs.getString("FIRSTNAME");
                String position = rs.getString("POSITION");
                int sal = rs.getInt("SALARY");

            }
            rs.first();
            System.out.println("Lastname" + "\t" + "Firstname" + "\t" + "Position" + "\t" + "Salary");
            while (rs.next()) {

                if (rs.getString("LASTNAME").charAt(0) == 'R' && rs.getInt("SALARY") > 58543) {

                    System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
                            + rs.getString(3) + "\t" + rs.getInt("Salary"));

                }
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }

    }

    /**
     * generates report 1
     */
    public void report1() {
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM SAILOR WHERE SALARY = (SELECT MAX (SALARY) FROM SAILOR\n"
                    + "                  WHERE CAST(POSITION AS VARCHAR(128))='Captain')"
                    + "                   OR SALARY = (SELECT MAX (SALARY) FROM SAILOR"
                    + "                  WHERE CAST(POSITION AS VARCHAR(128))='Engineer')"
                    + "                  OR SALARY = (SELECT MAX (SALARY) FROM SAILOR"
                    + "                  WHERE CAST (POSITION AS VARCHAR(128))='Doctor')"
                    + "OR SALARY = (SELECT MAX (SALARY) FROM SAILOR"
                    + "                  WHERE CAST(POSITION AS VARCHAR(128))='Cook')"
                    + "OR SALARY = (SELECT MAX (SALARY) FROM SAILOR"
                    + "                  WHERE CAST(POSITION AS VARCHAR(128))='Mechanic')");
            System.out.println("Lastname" + "\t" + "Firstname" + "\t" + "Position" + "\t" + "Salary");
            while (rs3.next()) {
                String lname = rs3.getString(1);
                String fname = rs3.getString(2);
                String pos = rs3.getString(3);
                int sal = rs3.getInt(4);
                System.out.println(lname + "\t" + fname + "\t" + pos + "\t" + sal);

            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    /**
     * generates report2
     */
    public void report2() {
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM SAILOR WHERE SALARY = (SELECT MIN(SALARY)FROM SAILOR)"
                    + " OR SALARY = (SELECT MAX(SALARY)FROM SAILOR)"
            );
            System.out.println("Lastname" + "\t" + "Firstname" + "\t" + "Position" + "\t" + "Salary");
            while (rs1.next()) {
                String lname = rs1.getString(1);
                String fname = rs1.getString(2);
                String pos = rs1.getString(3);
                int sal = rs1.getInt(4);

                System.out.println(lname + "\t" + fname + "\t" + pos + "\t" + sal);

            }
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM SAILOR WHERE SALARY > (SELECT AVG (SALARY) FROM SAILOR)");
            System.out.println("********************************************************************");
            System.out.println("Sailors having salaries above average");

            System.out.printf("%-25s%-25s%-25s%-25s","Lastname","Firstname","Position","Salary");
            System.out.println("");
            while (rs2.next()) {
                String lname = rs2.getString(1);
                String fname = rs2.getString(2);
                String pos = rs2.getString(3);
                int sal = rs2.getInt(4);

                System.out.printf("%-25s%-25s%-25s%-25d%n",lname,fname,pos,sal);

            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

}
