<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="services.ControleConnexion"%>

<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="CSS/Authentification.css" />
        <title>Authentification</title>
    </head>
    <body >
        <div class="center" id="Login">
           <center> <h1>Login</h1></center>
            <form action="/ProjetJ2EE/connexion" method="post">
                <center><input type="text" name="login" placeholder="Username" required></center>
                <br>
                <center><input type="password" name="password" placeholder="Password" required></center>
                <br>
                <center> <input type="submit" value="Connection" id="connection"></center>
            </form>
        </div>
    </body>
    <footer>

    </footer>
</html>