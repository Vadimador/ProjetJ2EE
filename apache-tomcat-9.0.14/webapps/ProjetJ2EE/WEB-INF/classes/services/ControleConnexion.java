package services;

import java.io.IOException;
import java.io.PrintWriter;
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
import mediatek2020.items.Utilisateur;


@WebServlet("/connexion")

public class ControleConnexion extends HttpServlet {
	private String login;
	private String password;
	private static final long serialVersionUID = 1L;
	
	public ControleConnexion() {
		super();
		this.login = "";
		this.password = "";
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
        String pwd = request.getParameter("password");
        
        Utilisateur user = Mediatheque.getInstance().getUser(login, pwd);
       
        if(user != null) {
        	//création de la session
      		HttpSession session = request.getSession(true);
      		
      		session.setAttribute("user_connecte", user);
      		
      		List<Document> listeDocuments = Mediatheque.getInstance().tousLesDocuments();
      		
      		if(user.isBibliothecaire()) {
      			session.setAttribute("listeDocs", listeDocuments);
      			getServletContext().getRequestDispatcher("/services/AccueilBiblio.jsp").forward(request, response);
      		}
      		else if(!user.isBibliothecaire()) {
      			List<Document> docsAvailable = new ArrayList<>();
      			List<Document> docsToReturn = new ArrayList<>();
      			
      			for(Document doc : listeDocuments) {
      				//Si un doc n'a pas de propriétaire
      				if((Integer)doc.data()[4] == 0) {
      					docsAvailable.add(doc);
      				}
      			}
      			
      			for(Document doc : listeDocuments) {
      				//Si un doc n'a pas de propriétaire
      				if(doc.data()[4] == user.data()[0]) {
      					docsToReturn.add(doc);
      				}
      			}
      			
      			String disabledOrNotR = (docsToReturn.size() <= 0)?"disabled":"";
      			String disabledOrNotA = (docsAvailable.size() <= 0)?"disabled":"";
      			
      			session.setAttribute("listeDocToReturn", docsToReturn);
      			session.setAttribute("listeDocDispo", docsAvailable);
      			session.setAttribute("disabledR", disabledOrNotR);
      			session.setAttribute("disabledA", disabledOrNotA);
      			getServletContext().getRequestDispatcher("/services/AccueilAbo.jsp").forward(request, response);
      		}
      		
        }
        else {
        	response.getWriter().print("erreur");
        }
      
	} 
}
