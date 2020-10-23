package com.dixon.javafx_crud;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * @author dixon
 *
 */
public class JavaFxMainController implements Initializable {

	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField designation;
	@FXML
	private TextField company;
	@FXML
	private TableView<Employees> tvEmployees;
	@FXML
	private TableColumn<Employees, Integer> colId;
	@FXML
	private TableColumn<Employees, String> colName;
	@FXML
	private TableColumn<Employees, String> colDesignation;
	@FXML
	private TableColumn<Employees, String> colCompany;
	@FXML
	private TableColumn<Employees, String> colSelect;
	@FXML
	private CheckBox selectAll;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;

	@FXML
	private void handleButtonAction(ActionEvent event) {

		if (event.getSource() == btnInsert) {
			insertRecord();
		} else if (event.getSource() == btnUpdate) {
			updateRecord();
		} else if (event.getSource() == btnDelete) {
			deleteButton();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		showEmployees();

		selectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				System.out.println("Select all clicked");

				ObservableList<Employees> list = tvEmployees.getItems();

				for (Employees employee : list) {

					if (selectAll.isSelected()) {

						employee.getCheckBox().setSelected(true);
					} else {

						employee.getCheckBox().setSelected(false);
					}
				}

			}

		});

		tvEmployees.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				System.out.println(tvEmployees.getSelectionModel().getSelectedItem().getName());

			}
		});

	}

	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "123456");
			return conn;
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return null;
		}
	}

	public ObservableList<Employees> getEmployeesList() {
		ObservableList<Employees> bookList = FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "SELECT * FROM employee";
		Statement st;
		ResultSet rs;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			Employees employees;
			while (rs.next()) {
				employees = new Employees(rs.getInt("id"), rs.getString("name"), rs.getString("designation"),
						rs.getString("company"));
				bookList.add(employees);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookList;
	}

	public void showEmployees() {
		ObservableList<Employees> list = getEmployeesList();

		colId.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<Employees, String>("name"));
		colDesignation.setCellValueFactory(new PropertyValueFactory<Employees, String>("designation"));
		colCompany.setCellValueFactory(new PropertyValueFactory<Employees, String>("company"));
		colSelect.setCellValueFactory(new PropertyValueFactory<Employees, String>("checkBox"));
		tvEmployees.setItems(list);
	}

	private void insertRecord() {
		String query = "INSERT INTO employee VALUES (" + id.getText() + ",'" + name.getText() + "','"
				+ designation.getText() + "','" + company.getText() + "')";
		executeQuery(query);
		showEmployees();
	}

	private void updateRecord() {
		String query = "UPDATE  employee SET name  = '" + name.getText() + "', designation = '" + designation.getText()
				+ "', company = " + company.getText() + "";
		executeQuery(query);
		showEmployees();
	}

	private void deleteButton() {
		String query = "DELETE FROM employee WHERE id =" + id.getText() + "";
		executeQuery(query);
		showEmployees();
	}

	private void executeQuery(String query) {
		Connection conn = getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
