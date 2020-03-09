package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2020.*;
import mediatek2020.items.*;

// classe mono-instance  dont l'unique instance est injectée dans Mediatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	// Jean-François Brette 01/01/2018
		
		private static String url_jdbc = "jdbc:oracle:thin:@localhost:1521:XE";
		private static String login = "SYSTEM";
		private static String pwd = "Ob6o6cE6";
		private static Connection connexionBD;
	
		static {
			initialization();
		}
		
		public MediathequeData() {
			
		}
		
		/**
		 * Fonction statique pour se connecter à la base de donnée Oracle
		 * @return connexionBD : un object Connection
		 * @throws ClassNotFound ==> la classe oracle.jdbc.OracleDriver n'a pas été trouvé
		 */
		private static Connection Connexion() throws Exception {
			if(Class.forName("oracle.jdbc.OracleDriver") == null) throw new ClassNotFoundException();
			connexionBD = DriverManager.getConnection(url_jdbc, login, pwd);
			return connexionBD;
		}
		
		/**
		 * Initialisation au lancement de la classe
		 */
		
		private static void initialization() {
			Mediatheque.getInstance().setData(new MediathequeData());
			try {
				Connection connec = Connexion();
			} catch (Exception e) {
				System.err.println("Connexion à la base de donnée a échoué");
			}
		}
		
		//Recupere toutes les données d'un utilisateur qui se connecte
		private static ResultSet infoUtilisateur(String login, String password) throws Exception {
			String query = "SELECT * FROM utilisateur WHERE UserLogin = ? AND MotDePasse = ?";
			PreparedStatement pstm = connexionBD.prepareStatement(query);
			pstm.setString(1, login); 
			pstm.setString(2, password);
			ResultSet res = pstm.executeQuery();
			return res;
		}
		
		private static ResultSet test() throws Exception{
			String query="SELECT nomUtilisateur FROM utilisateur";
			PreparedStatement pstm = connexionBD.prepareStatement(query);
			//pstm.setInt(1, 1);
			ResultSet res = pstm.executeQuery();
			//System.out.println(res.next());
			return res;
		}
		
		// renvoie la liste de tous les documents de la bibliothèque
		@Override
		public List<Document> tousLesDocuments() {
			List<Document> liste = new ArrayList<>();
			String query = "SELECT * FROM document";
			try {
				PreparedStatement pstm = connexionBD.prepareStatement(query);
				ResultSet res = pstm.executeQuery();
				
				if (res.next() == false) { 
					System.out.println("ResultSet vide");
				}
				else {
					do {
						int id = res.getInt("IdDocument");
						
						boolean stateReserved;
						boolean stateAvailable;
						
						if(res.getInt("IsReserver") == 0)
							stateReserved = false;
						else
							stateReserved = true;
						
						if(res.getInt("IsDisponible") == 0)
							stateAvailable = false;
						else
							stateAvailable = true;
						
						int userID = res.getInt("UserID");
						
						liste.add(new DocumentBD(id, stateReserved, stateAvailable, userID));
						
						
					}while(res.next());
				}
								
			} catch (SQLException e) {
				System.out.println("Erreur d'execution de la requete");
			}
			//La liste contient une liste de document avec les données de la bdd
			return liste;
		}

		// va récupérer le User dans la BD et le renvoie
		// si pas trouvé, renvoie null
		@Override
		public UtilisateurBD getUser(String login, String password) {
			try {
				ResultSet res = infoUtilisateur(login, password);
				
				if(res.next()) {
					int type = res.getInt("typeUtilisateur");
					
					switch(type) {
						case 0 : return new UtilisateurBD(res.getInt("IdUtilisateur"),res.getString("nomUtilisateur"), login, password, typeUtilisateur.abonné); 
						case 1 : return new UtilisateurBD(res.getInt("IdUtilisateur"),res.getString("nomUtilisateur"), login, password, typeUtilisateur.bibliothécaire); 
						default : return null;
					}
				} else {
					return null;
				}
			}catch(Exception e){
				return null;
			}
		}

		// va récupérer le document de numéro numDocument dans la BD
		// et le renvoie
		// si pas trouvé, renvoie null
		@Override
		public Document getDocument(int numDocument) {
			return null;
		}

		@Override
		public void nouveauDocument(int type, Object... args) {
			// args[0] -> le titre
			// args [1] --> l'auteur
			// etc...
		}
		
		
		public static void main(String[] args) throws Exception {
			//System.out.println(Mediatheque.getInstance().getUser("user1","user1").name());
			ResultSet res = test();
			System.out.println(Mediatheque.getInstance().getUser("vadime","vadoom").name());
			
			if (res.next() == false) { 
				System.out.println("ResultSet vide");
			}
			else {
				do {
					String nom = res.getString("nomUtilisateur");
					
					System.out.println("Le nom est : " + nom);
				}while(res.next());
			}
		}
}
