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
		Object[] o = new Object[5];
		o[0] = this.id;
		o[1] = this.nom;
		o[2] = this.login;
		o[3] = this.mdp;
		o[4] = this.type;
		
		return o;
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

}
