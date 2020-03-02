package persistantdata;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

public class DocumentBD implements Document{
	private Class< ? extends Document>[] data;
	
	@Override
	public Object[] data() {
		// TODO Auto-generated method stub
		return this.data;
	}

	@Override
	public void emprunter(Utilisateur arg0) throws EmpruntException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rendre(Utilisateur arg0) throws RetourException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reserver(Utilisateur arg0) throws ReservationException {
		// TODO Auto-generated method stub
		
	}

}
