package persistantdata;

import mediatek2020.items.Utilisateur;

//Classe à modifier quand le jdbc fonctionnera 
public class UtilisateurBD implements Utilisateur {

	private int id;
	private String nom;
	private String login;
	private String mdp;
	private typeUtilisateur type;
	
	public UtilisateurBD(int id,String nom, String login, String password, typeUtilisateur type) {
		this.id = id;
		this.nom = nom;
		this.login = login;
		this.mdp = password;
		this.type = type;
	}

	@Override
	public Object[] data() {
		// ?????
		return null;
	}
	
	
	@Override
	public boolean isBibliothecaire() {
		if(this.type == typeUtilisateur.bibliothécaire)
			return true;
		else
			return false;
	}

	@Override
	public String name() {
		return this.nom;
	}
	
	public int getID() {
		return this.id;
	}

}
