package application;
import java.sql.*;
//import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class usersfx {
	Connection con=null;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/java_fx?characterEncoding=utf8";
			String username="root";
			String password="717273";
			con=DriverManager.getConnection(url,username,password);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<userModel> getUserList(){
		ObservableList<userModel> userList=FXCollections.observableArrayList();
		String sql="SELECT ID,NAME,PHONE from Contact";
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			userModel user;
			while(rs.next()) {
				user=new userModel(rs.getInt("ID"),rs.getString("NAME"),rs.getString("PHONE"));
				userList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	//Show User Details
	public void loadData() {
		ObservableList<userModel> list = getUserList();
		colId.setCellValueFactory(new PropertyValueFactory<userModel, Integer>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<userModel, String>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<userModel, String>("phone"));
		table.setItems(list);
	}	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnClear;

    @FXML
    private TableColumn<userModel, Integer> colId;

    @FXML
    private TableColumn<userModel, String> colName;

    @FXML
    private TableColumn<userModel, String> colPhone;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private TableView<userModel> table;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;
    @FXML
    void btnClearClicked(ActionEvent event) {
    	txtId.setText("");
		txtName.setText("");
		txtPhone.setText("");

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {
    	String id = txtId.getText();
		if (!txtId.getText().isEmpty()) {
 
			int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
 
			if (result == JOptionPane.YES_OPTION) {
				try {
					String sql = "delete from Contact where ID=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Deleted Success");
					btnClearClicked(event);
					loadData();
 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

    }

    @FXML
    void btnSaveClicked(ActionEvent event) {
    	String name = txtName.getText();
		String phone = txtPhone.getText();
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Name");
			txtName.requestFocus();
			return;
		}
		if (phone == null || phone.isEmpty() || phone.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Phone Number");
			txtPhone.requestFocus();
			return;
		}
		if (txtId.getText().isEmpty()) {
			try {
				String sql="insert into Contact (NAME,PHONE) values (?,?)";
				pst=con.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, phone);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data insert Success");
				btnClearClicked(event);
				loadData();
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
    }

    @FXML
    void btnUpdateClicked(ActionEvent event) {
    	String id = txtId.getText();
    	String name = txtName.getText();
		String phone = txtPhone.getText();
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Name");
			txtName.requestFocus();
			return;
		}
		if (phone == null || phone.isEmpty() || phone.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Phone Number");
			txtPhone.requestFocus();
			return;
		}
		if (!txtId.getText().isEmpty()) {
			try {
				String sql="update Contact set NAME=?,PHONE=? where ID=?";
				pst=con.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, phone);
				pst.setString(3,id);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data Updated Success");
				btnClearClicked(event);
				loadData();
				
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		

    }

    @FXML
    void tableClicked(MouseEvent event) {
    	userModel user=table.getSelectionModel().getSelectedItem();
		txtId.setText(String.valueOf(user.getId()));
		txtName.setText(user.getName());
		txtPhone.setText(String.valueOf(user.getPhone()));
//		txtPhone.setText(user.getPhone());
    }

    @FXML
    void initialize() {
       Connect();
       loadData();
    }

}
