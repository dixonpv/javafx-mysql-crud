package com.dixon.javafx_crud;

import javafx.scene.control.CheckBox;

/**
 * @author dixon
 *
 */
public class Employees {

    private int id;
    private String name;
    private String company;
    private String designation;
    private CheckBox checkBox;
    
    
	/**
	 * @param id
	 * @param name
	 * @param company
	 * @param designation
	 * @param checkBox
	 */
	public Employees(int id, String name, String designation,String company) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.company = company;
		this.checkBox = new CheckBox();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}


	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}


	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}


	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}


	/**
	 * @return the checkBox
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}


	/**
	 * @param checkBox the checkBox to set
	 */
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
    

}
