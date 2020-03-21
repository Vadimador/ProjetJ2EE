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

String disabledR = (String) session.getAttribute("disabledR");
String disabledA = (String) session.getAttribute("disabledA");
String nom = user.name();
 %>

<html>
    <head>
		<link rel="stylesheet" href="services/CSS/AccueilAbo.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
		<div id="TitreDiv">
			<h1> Menu Abonné</h1>
			<h3>Nom : <%= nom %></h3>
		</div>
		<div id="contenu" class="flex column">
			<div class="flex row" id="action">
				<div id="DocForm " class="bloc" >
					<h2>La liste des documents pouvant être emprunté :</h2>
					<form id="DocForm" action="/ProjetJ2EE/empruntDoc" method="POST">
						<select name="docAvailable" size="1">
							<% for (Document doc : listeDocAvailable) { %>
								<option value="<%= doc.data()[0] %>"><%= doc.data()[1] %></option> 
							<% } %>
						</select>
						<input type="submit" class="connection" value="emprunter" <%= disabledA %>>	
					</form>
				</div>
				<div id="NewDoc" class="bloc">
					<h2>La liste des documents pouvant être rendu :</h2>
					
					<form action="/ProjetJ2EE/rendreDoc" method="post">
							<select name="docToReturn" size="1">
								<% for (Document doc : listDocToReturn) { %>
									<option value="<%= doc.data()[0] %>"><%= doc.data()[1] %></option> 
								<% } %>
							</select>
							<input type="submit" class="connection" value="rendre" <%= disabledR %>>
					</form>
				</div>
			</div>
		</div>
    </body>
</html>