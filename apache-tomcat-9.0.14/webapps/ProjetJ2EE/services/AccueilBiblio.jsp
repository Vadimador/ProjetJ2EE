<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="mediatek2020.items.Utilisateur"%>
<%@ page import="mediatek2020.items.Document"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>

<%    
Utilisateur user = (Utilisateur) session.getAttribute("user_connecte"); // <- ne marche pas

String nom = user.name();
List<Document> listeDoc = (List<Document>) session.getAttribute("listeDocs");
%>

<html>
    <head>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="services/CSS/AccueilBiblio.css" />
        <title>Accueil Bibliothécaire</title>
    </head>
    <body>
		<div id="TitreDiv">
			<h1> Menu Bibliothécaire</h1>
			<h3>Nom : <%= nom %></h3>
		</div>
		<div id="contenu" class="flex column">
			<div class="flex row" id="action">
				<div id="DocForm " class="bloc" >
					<h2> Liste des documents : </h2>
					<form id="DocForm">
						<select name="documents" size="1">
								<% for (Document doc : listeDoc) { %>
									<option><%= doc.data()[1] %></option> 
								<% } %>
						</select>
					</form>
				</div>
				<div id="NewDoc" class="bloc">
					<h2>Pour ajouter un nouveau document, veuillez remplir ces champs</h2>
					
					<form action="/ProjetJ2EE/ajoutDoc" method="post">
							<center><p>Nom du document : </p><input type="text" name="nomDoc"></center>
							<br>
							<center> <input type="submit" id="connection" value="Ajouter le document"></center>
					</form>
				</div>
			</div>
		</div>
    </body>
</html>