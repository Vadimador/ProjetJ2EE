package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import mediatek2020.*;
import mediatek2020.items.*;

// classe mono-instance  dont l'unique instance est injectée dans Mediatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	// Jean-François Brette 01/01/2018
		
		private static String url_jdbc = "jdbc:oracle:thin:@localhost:1521:XE";
		private static String login = "SYSTEM";
		private static String pwd = "znbakst3";
		private static Connection connexionBD;
		
		
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
		static {
			initialization();
		}
		
		private static void initialization() {
			Mediatheque.getInstance().setData(new MediathequeData());
			try {
				Connection connec = Connexion();
			} catch (Exception e) {
				System.err.println("Connexion à la base de donnée a échoué");
			}
		}
		
		private static ResultSet test() throws Exception{
			String query="SELECT nomUtilisateur FROM utilisateur WHERE IdUtilisateur = ?";
			PreparedStatement pstm = connexionBD.prepareStatement(query);
			pstm.setInt(1, 1);
			ResultSet res = pstm.executeQuery();
			return res;
		}

		
		private static ResultSet infoUtilisateur(String login, String password) throws Exception {
			String query = "SELECT idUtilisateur,NomUtilisateur,login, typeUtilisateur FROM utilisateur WHERE login = ? AND MotDePasse = ?";
			PreparedStatement pstm = connexionBD.prepareStatement(query);
			pstm.setString(1, login); 
			pstm.setString(2, password);
			ResultSet res = pstm.executeQuery();
			return res;
		}
		
		
		//Constructeur
		public MediathequeData() {}

		// renvoie la liste de tous les documents de la bibliothèque
		@Override
		public List<Document> tousLesDocuments() {
			return null;
		}

		// va récupérer le User dans la BD et le renvoie
		// si pas trouvé, renvoie null
		@Override
		public Utilisateur getUser(String login, String password) {
			try {
				ResultSet res = infoUtilisateur(login, password);
				
				if(res.next()) {
					int type = res.getInt("biblioUtilisateur");
					
					switch(type) {
						case 0 : return new UtilisateurBD(res.getInt("IdUtilisateur"), login, password, typeUtilisateur.abonné); 
						case 1 : return new UtilisateurBD(res.getInt("IdUtilisateur"), login, password, typeUtilisateur.bibliothécaire); 
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
			
			if (res.next() == false) { 
				System.out.println("ResultSet vide"); 
			}
			else {
				while(res.next()) {
					String nom = res.getString("nomUtilisateur");
					
					System.out.println("Le nom est : " + nom);
				}
			}
		}
}
