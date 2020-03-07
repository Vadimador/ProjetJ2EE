package persistantdata;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

public class DocumentBD implements Document{
	private Class< ? extends Document>[] data;
	
	private int id;
	private boolean IsReserved;
	private boolean IsAvailable;
	private int userID;
	
	public DocumentBD(int id, boolean res, boolean avail, int ID) {
		this.id = id;
		this.IsReserved = res;
		this.IsAvailable = avail;
		this.userID = ID;
	}
	
	@Override
	public Object[] data() {
		// TODO Auto-generated method stub
		return this.data;
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
