package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2020.Mediatheque;

@WebServlet("/ajoutDoc")
public class AjoutDocument extends HttpServlet {
	private String nom;

	private static final long serialVersionUID = 1L;
	
	public AjoutDocument() {
		super();
		this.nom = "";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Ajout d'un doc à faire
		String docName = request.getParameter("nomDoc");
		
		//le 1er argument ne sert à rien car je ne l'utilise pas
		Mediatheque.getInstance().nouveauDocument(1, docName);
		
		getServletContext().getRequestDispatcher("/services/AccueilBiblio.jsp").forward(request, response);
	}
}
