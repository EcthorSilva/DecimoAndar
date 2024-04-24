package br.com.decimoandar.dao;

import br.com.decimoandar.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDao {
    public void createUser(User user){

        try {
            String SQL = "INSERT INTO DECIMO_ANDAR.Usuario (NOME_COMPLETO, EMAIL, CPF_CNPJ, SENHA) VALUES (?, ?, ?, ?)";

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            if (connection != null) {
                System.out.println("Success in database connection");
            } else {
                System.out.println("Failed to connect to the database");
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getDocPfPj());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.execute();

            System.out.println("Success in insert user");

            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void readUser(User user) {
        try {
            String SQL = "SELECT NOME_COMPLETO, EMAIL, CPF_CNPJ FROM DECIMO_ANDAR.USUARIO WHERE ID = ?";
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setName(resultSet.getString("NOME_COMPLETO"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setDocPfPj(resultSet.getString("CPF_CNPJ"));
                return true;
            }
            connection.close();
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void UpdateUser(User user){
        try{
            String SQL = "UPDADE DECIMO_ANDAR.Usarios SET NOME_COMPLETO = ?" +
                                                          "EMAIL = ?" +
                                                          "CPF_CNPJ = ? " +
                                                          "SENHA = ?" +
                                                          "WHERE ID = ?";

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            if (connection != null) {
                System.out.println("Success in database connection");
            } else {
                System.out.println("Failed to connect to the database");
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getDocPfPj());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getIdUser());

            preparedStatement.execute();

            System.out.println("Success in insert user");

            connection.close();

        }catch (Exception ex){
            System.out.println("Falha ao fazer o update no usuarios" + ex.getMessage());
        }
    }

    public void deleteUser(User user){

        try {
            String SQL = "DELETE FROM DECIMO_ANDAR.Usuario WHERE ID = ?";

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            if (connection != null) {
                System.out.println("Success in database connection");
            } else {
                System.out.println("Failed to connect to the database");
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, user.getIdUser());

            preparedStatement.execute();

            System.out.println("Success in delete user");

            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public boolean emailExists(String email) {
        try {
            String SQL = "SELECT COUNT(*) FROM CADASTRO WHERE EMAIL = ?";
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public boolean docExists(String doc) {
        try {
            String SQL = "SELECT COUNT(*) FROM CADASTRO WHERE DOC = ?";
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, doc);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public boolean validateCredentials(String email, String password) {
        try {
            String SQL = "SELECT COUNT(*) FROM DECIMO_ANDAR.USUARIO WHERE EMAIL = ? AND SENHA = ?";
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
