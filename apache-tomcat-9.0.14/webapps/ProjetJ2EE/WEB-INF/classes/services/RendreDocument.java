package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

@WebServlet("/rendreDoc")
public class RendreDocument extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private String numDoc;
	
	public RendreDocument() {
		super();
		numDoc = "";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		Utilisateur currentUser = (Utilisateur) session.getAttribute("user_connecte");
		
		//doc à emprunter
  		numDoc = request.getParameter("docNumberToReturn");
  		
		//Doc à rendre
  		Document doc = Mediatheque.getInstance().getDocument(Integer.parseInt(numDoc));
  		
		try {
			doc.rendre(currentUser);
		} catch (RetourException e) {
			e.printStackTrace();
		}
  		
		//Actualisation de la page 
  		List<Document> listeDocuments = Mediatheque.getInstance().tousLesDocuments();
  		List<Document> docsAvailable = new ArrayList<>();
		List<Document> docsToReturn = new ArrayList<>();
			
			for(Document docu : listeDocuments) {
				//Si un doc n'a pas de propriétaire
				if(docu.data()[4] == null) {
					docsAvailable.add(docu);
				}
			}
			
			for(Document docu : listeDocuments) {
				//Si un doc n'a pas de propriétaire
				if(docu.data()[4] == currentUser.data()[0]) {
					docsToReturn.add(docu);
				}
			}
			
			session.setAttribute("listeDocToReturn", docsToReturn);
			session.setAttribute("listeDocDispo", docsAvailable);
		getServletContext().getRequestDispatcher("/services/AccueilBiblio.jsp").forward(request, response);
	}
}
