<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="mediatek2020.items.Utilisateur"%>
<%@ page import="mediatek2020.items.Document"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>

<%    
Utilisateur user = (Utilisateur) session.getAttribute("user_connecte"); 
List<Document> listDocToReturn = (List<Document>) session.getAttribute("listeDocToReturn");
List<Document> listeDocAvailable = (List<Document>) session.getAttribute("listeDocDispo");

String nom = user.name();
 %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <h1> Bienvenue <%= nom %>, tu es un abonné</h1> 
		<br><br><br><br>
		
		<h1> La liste des documents pouvant être emprunté :</h1>
		
		<form action="/ProjetJ2EE/empruntDoc" method="post">
			<select name="docAvailable" size="1">
				<% for (Document doc : listeDocAvailable) { %>
					<option name="docNumberAvailable"><%= doc.data()[0] %></option> 
				<% } %>
			</select>
			<input type="submit" value="emprunter">
		</form>
		
		<br><br><br><br>
		
		<h1> La liste des documents pouvant être rendu :</h1>
		
		<form action="/ProjetJ2EE/rendreDoc" method="post">
			<select name="docToReturn" size="1">
				<% for (Document doc : listDocToReturn) { %>
					<option name="docNumberToReturn"><%= doc.data()[0] %></option> 
				<% } %>
			</select>
			<input type="submit" value="rendre">
		</form>
    </body>
</html>