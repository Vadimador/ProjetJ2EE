package persistantdata;

import mediatek2020.items.Utilisateur;

//Classe à modifier quand le jdbc fonctionnera 
public class UtilisateurBD implements Utilisateur {

	private int id;
	private String nom;
	private String login;
	private String mdp;
	private typeUtilisateur type;
	
	public UtilisateurBD(int id, String login, String password, typeUtilisateur type) {
		this.id = id;
		this.login = login;
		this.mdp = password;
		this.type = type;
	}

	@Override
	public Object[] data() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBibliothecaire() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
