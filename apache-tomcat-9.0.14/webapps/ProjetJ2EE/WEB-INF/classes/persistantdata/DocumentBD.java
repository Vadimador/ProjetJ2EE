package persistantdata;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

public class DocumentBD implements Document{
	private Class< ? extends Document>[] data;
	
	private int id;
	private String nom;
	private boolean IsReserved;
	private boolean IsAvailable;
	private int userID;
	
	public DocumentBD(int id,String n, boolean res, boolean avail, int ID) {
		this.id = id;
		this.nom = n;
		this.IsReserved = res;
		this.IsAvailable = avail;
		this.userID = ID;
	}
	
	@Override
	public Object[] data() {
		Object[] o = new Object[5];
		o[0] = this.id;
		o[1] = this.nom;
		o[2] = IsReserved;
		o[3] = IsAvailable;
		o[4] = userID;
		
		return o;
	}
	
	public String getNom() {
		return this.nom;
	}

	@Override
	public void emprunter(Utilisateur user) throws EmpruntException {
		if(this.IsReserved && this.userID == ((UtilisateurBD) user).getID()) {
			this.IsAvailable = false;
		}
	}

	@Override
	public void rendre(Utilisateur user) throws RetourException {
		
	}

	@Override
	public void reserver(Utilisateur user) throws ReservationException {
		if(this.IsAvailable && !this.IsReserved && user == null) {
			this.IsReserved = true;
			this.userID = ((UtilisateurBD) user).getID();
		}
	}

}
