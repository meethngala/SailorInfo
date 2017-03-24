/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sailorinfo;

/**
 * class for sailors
 * @author Meeth
 */
public class Sailor {
    String lastname;
    String firstname;
    String position;
    int salary;

    /**
     * get last name
     * @return lname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * set last name
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * get the first name
     * @return fname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * set the first name
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * get post
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * set the post
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * get salary
     * @return
     */
    public int getSalary() {
        return salary;
    }

    /**
     * set salary
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }
    
}
