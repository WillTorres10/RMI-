package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class banco {
	
	private Connection conexao = null;
	//Contrutor da Classe
	public banco() {
		
	}
    //Conecta ao banco de dados
    public void connectar() throws ClassNotFoundException{
    	String driverName = "com.mysql.jdbc.Driver";                        
    	Class.forName(driverName);
    	String serverName = "localhost";
        String mydatabase ="rmi";        
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "rmi";              
        String password = "123456789";
        try {
        	this.conexao = DriverManager.getConnection(url, username, password);
        	System.out.println("Sucesso ao conectar ao banco de dados");
        }catch(SQLException e){
        	System.out.println("Falha ao conectar ao banco de dados");
        }
    }
    
    public void inserirNovoUser(String nome, Double imc) {
    	// the mysql insert statement
        String query = " insert into PACIENTE (nome, imc)"
          + " values (?, ?)";
        try {
        	PreparedStatement preparedStmt = (PreparedStatement) this.conexao.prepareStatement(query);
			preparedStmt.setString (1, nome);
			preparedStmt.setDouble(2, imc);
	        preparedStmt.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o novo usuário");
		}
    }
    
    public boolean verificaUsuario(String nome){
		String query = "SELECT count(*) FROM PACIENTE WHERE nome = '"+nome+"'";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = (PreparedStatement) this.conexao.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next()) {
				int numberOfRows = rs.getInt(1);
				if (numberOfRows == 0) {
					return false;
				}
				else {
					return true;
				}
			} 
			else{
			    System.out.println("error: could not get the record counts");
			}
		} catch (SQLException e) {
			System.out.println("Falha ao Verificar o nome");	
			System.out.println(e);
		}
    	return false;
    }
    
    public double atualizarUsuario(String nome, Double imc) {
    	String buscarQuery = "SELECT * FROM PACIENTE WHERE nome = '"+nome+"'";
    	PreparedStatement preparedStmt;
    	try {
			preparedStmt = (PreparedStatement) this.conexao.prepareStatement(buscarQuery);
			ResultSet rs = preparedStmt.executeQuery();
			String vezesText = null, imcAntigoText = null;
			while (rs.next()) {
				vezesText = rs.getString(2);
				imcAntigoText = rs.getString(3);
				break;
			}			
			
			int vezes = Integer.parseInt(vezesText);
			Double imcAntigo = Double.parseDouble(imcAntigoText);
			//Atualizando
			try {
				String query = "update PACIENTE set qtd_cst = ?, imc = ? where nome = ?";
			    preparedStmt =(PreparedStatement) this.conexao.prepareStatement(query);
			    preparedStmt.setInt   (1, vezes+1);
			    preparedStmt.setDouble(2, imc);
			    preparedStmt.setString(3, nome);
				preparedStmt.executeUpdate();
				return imc - imcAntigo;
			}catch (Exception e) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println("Falha ao Verificar o nome");	
			System.out.println(e);
		}
    	/*
    	String query = " update PACIENTE (nome, imc)"
          + " values (?, ?)";
        try {
        	PreparedStatement preparedStmta = (PreparedStatement) this.conexao.prepareStatement(query);
			preparedStmta.setString (1, nome);
			preparedStmta.setDouble(2, imc);
	        preparedStmta.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o novo usuário");
		}*/
		return imc;
    }
}
