module javafx {
    requires javafx.controls;
    requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	
	opens com.dixon.javafx_crud;
}