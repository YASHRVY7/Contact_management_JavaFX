
# JavaFX Contact Management System

A JavaFX-based desktop application for managing contacts, integrated with a MySQL database for storing, updating, deleting, and retrieving contact information. The application uses JDBC for database connectivity .

## Features

- **Create**: Add new contacts to the database.
- **Read**: View the list of contacts in a TableView.
- **Update**: Modify existing contact details.
- **Delete**: Remove a contact from the database.
- **Clear**: Clear the input fields for fresh data entry.
  
## Technologies Used

- **JavaFX**: For building the graphical user interface (GUI).
- **MySQL**: For storing the contact data.
- **JDBC**: For connecting JavaFX with the MySQL database.
- **Scene Builder**: For designing the JavaFX UI layout in FXML.


## Prerequisites

1. **Java JDK** (Version 8 or higher)
2. **MySQL Database** and **JDBC** driver for MySQL
3. **Scene Builder** (optional but recommended for UI design)
4. **IDE** (e.g., IntelliJ IDEA, Eclipse, or NetBeans)

## Setting Up the Environment

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/Contact_management_JavaFX.git
    ```

2. Install **Java JDK** and set up your **IDE** to run JavaFX applications.

3. Download and install **MySQL**. Make sure the MySQL JDBC driver is included in the project.

4. **Create the Database**:
   - Open MySQL Workbench (or any MySQL client) and run the following SQL script to create the database and the `contacts` table:
   
     ```sql
     CREATE DATABASE contact_management;
     USE contact_management;

     CREATE TABLE contacts (
         id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(100),
         phone_number VARCHAR(15)
     );
     ```

5. **Configure Database Connection**:
   - Open the `DatabaseConnection.java` file and update the MySQL connection details (username, password, and database name).

   Example:
   ```java
   public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_management", "root", "your-password");
   }
# OUTPUT
![image](https://github.com/user-attachments/assets/363aedc0-426e-4a62-be0e-12b6e0d0862d)

