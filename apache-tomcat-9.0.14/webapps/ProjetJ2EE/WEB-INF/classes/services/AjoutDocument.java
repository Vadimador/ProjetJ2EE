package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.Document;

@WebServlet("/ajoutDoc")
public class AjoutDocument extends HttpServlet {
	private String nom;

	private static final long serialVersionUID = 1L;
	
	public AjoutDocument() {
		super();
		this.nom = "";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		HttpSession session = request.getSession(true);

		//Ajout d'un doc à faire
  		nom = request.getParameter("nomDoc");
		
		//le 1er argument ne sert à rien car je ne l'utilise pas
		Mediatheque.getInstance().nouveauDocument(1, nom);
		
		//On actualise la liste
		List<Document> listeDocuments = Mediatheque.getInstance().tousLesDocuments();
		session.setAttribute("listeDocs", listeDocuments);
		
		getServletContext().getRequestDispatcher("/services/AccueilBiblio.jsp").forward(request, response);
	}
}
