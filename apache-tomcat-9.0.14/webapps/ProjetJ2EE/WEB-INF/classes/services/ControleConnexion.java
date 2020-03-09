package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
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
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/services/Authentification.jsp").forward(request, response);
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
        String pwd = request.getParameter("password");
        
        Utilisateur user = Mediatheque.getInstance().getUser(login, pwd);
        //System.out.println(user.get);
        if(user != null) {
        	//cr�ation de la session
      		HttpSession session = request.getSession(true);
      		
      		session.setAttribute("user_connecte", user);
      		
      		getServletContext().getRequestDispatcher("/services/Accueil.jsp").forward(request, response);
        }
        else {
        	response.getWriter().print("else");
        }
      
	} 
}
