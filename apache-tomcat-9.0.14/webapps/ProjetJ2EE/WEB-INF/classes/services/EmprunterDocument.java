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
import mediatek2020.items.Utilisateur;

@WebServlet("/empruntDoc")
public class EmprunterDocument extends HttpServlet {
	
	private String numDoc;
	
	private static final long serialVersionUID = 1L;
	
	public EmprunterDocument() {
		super();
		numDoc = "";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);

		//doc à emprunter
  		numDoc = request.getParameter("docNumberAvailable");
  		
  		//Emrpunt du document
  		Document doc = Mediatheque.getInstance().getDocument(Integer.parseInt(numDoc));
  		Utilisateur currentUser = (Utilisateur) session.getAttribute("user_connecte");
  		try {
			doc.emprunter(currentUser);
		} catch (EmpruntException e) {
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
