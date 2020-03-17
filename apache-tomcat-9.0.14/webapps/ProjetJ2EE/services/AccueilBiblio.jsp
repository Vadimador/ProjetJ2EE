<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="mediatek2020.items.Utilisateur"%>
<%@ page import="mediatek2020.items.Document"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>

<% 
//Utilisateur user = request.getParameter("user_connecte");    
Utilisateur user = (Utilisateur) session.getAttribute("user_connecte"); // <- ne marche pas
//session.getAttribute("user_connecte");

String nom = user.name();
List<Document> listeDoc = (List<Document>) session.getAttribute("listeDocs");
//String nomDoc = (String) listeDoc.get(0).data()[1];
%>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <h1> Bienvenue <%= nom %>, tu es un bibliothécaire</h1> 
		<br><br><br>
		<h3> Voici la liste des documents : </h3>
		
		<form>
			<select name="documents" size="1">
				<% for (Document doc : listeDoc) { %>
					<option><%= doc.data()[1] %></option> 
				<% } %>
			</select>
			
		</form>
    </body>
</html>