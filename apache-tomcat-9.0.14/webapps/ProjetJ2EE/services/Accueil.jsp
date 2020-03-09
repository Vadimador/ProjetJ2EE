<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="mediatek2020.items.Utilisateur"%>

<!DOCTYPE html>

<% 
//Utilisateur user = request.getParameter("user_connecte");    
Utilisateur user = (Utilisateur) session.getAttribute("user_connecte"); // <- ne marche pas
//session.getAttribute("user_connecte");

String nom = user.name();
 %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
         <h1> Bienvenue <%= nom %></h1> 
        </body>
</html>