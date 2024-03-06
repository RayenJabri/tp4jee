package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.IBoissonDao;
import dao.BoissonDaoImpl;
import metier.entities.Boisson;
import org.apache.catalina.connector.Response;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IBoissonDao metier;
@Override
public void init() throws ServletException {
metier = new BoissonDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("boissons.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
BoissonModele model= new BoissonModele();
model.setMotCle(motCle);
List<Boisson> bois = metier.boissonsParMC(motCle);
model.setBoissons(bois);
request.setAttribute("model", model);
request.getRequestDispatcher("boissons.jsp").forward(request,response);
}

else if (path.equals("/saisie.do") )
{
request.getRequestDispatcher("saisieBoisson.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
double prix = Double.parseDouble(request.getParameter("prix"));
Boisson b = metier.save(new Boisson(nom,prix));
request.setAttribute("boisson", b);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 metier.deleteBoisson(id);
 response.sendRedirect("chercher.do?motCle=");
}
else if (path.equals("/editer.do") )
{
Long id= Long.parseLong(request.getParameter("id"));
 Boisson b = metier.getBoisson(id);
 request.setAttribute("boisson", b);
request.getRequestDispatcher("editerBoisson.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
double prix =
Double.parseDouble(request.getParameter("prix"));
Boisson b = new Boisson();
b.setIdBoisson(id);
b.setNomBoisson(nom);
b.setPrix(prix);
metier.updateBoisson(b);
request.setAttribute("boisson", b);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}
@Override
protected void doPost(HttpServletRequest request,
 HttpServletResponse response) throws
ServletException, IOException {
doGet(request,response);
}
}