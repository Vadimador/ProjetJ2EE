package persistantdata;

import java.sql.*;
import java.util.Scanner;


public class AccesBD {
	
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public static void main(String[] args) throws SQLException {
		//On enregistre le pilote
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				
		//Creation de la connexion
		Connection connect = DriverManager.getConnection(url,"ETUDIANT","ETUDIANT");
		
		//On récupère le paramètre 
		Scanner clavier = new Scanner(System.in);
		System.out.println("Saisissez votre nom :");
		String nomUser = clavier.nextLine();
		
		//Creation d'un PreparedStatement pour une requete
		//On met un ? là où on ajoutera un element (même principe que les requêtes en PHP)
		String req = "SELECT Login, MotDePasse FROM Utilisateur WHERE NOMUTILISATEUR = ?";
		PreparedStatement stmt = connect.prepareStatement("SELECT Login, MotDePasse FROM Utilisateur");
		
		//stmt.setString(1,nomUser);
		
		//Execution de la requete /!\ PAS DE REQUETE DANS LE executeQuery() SI PreparedStatement /!\
		ResultSet res = stmt.executeQuery();
		
		System.out.println(res.next());
		String login = res.getString("Login");
		String mdp = res.getString("MotDePasse");
		
		System.out.println("Monsieur " + nomUser + " Votre login est : " + login + " et votre mot de passe est : " + mdp);
		
		//Affichage de la requete
		while(res.next()) {
			//String login = res.getString("Login");
			//String mdp = res.getString("MotDePasse");
			
			System.out.println("Monsieur " + nomUser + " Votre login est : " + login + " et votre mot de passe est : " + mdp);
		}
		
		connect.close();
		stmt.close();
		res.close();
	}
			
}

