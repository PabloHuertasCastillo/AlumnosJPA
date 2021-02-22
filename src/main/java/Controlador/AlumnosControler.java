/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
public class AlumnosControler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ArrayList<String> grupos;
    private String rutaFicheros;
    private String rutaFicheroGrupoA;
    private String rutaFicheroGrupoB;
    private ArrayList<Alumno> listadoGrupoA = null;
    private ArrayList<Alumno> listadoGrupoB = null;

    public void init(ServletConfig config) throws ServletException {

        listadoGrupoA = new ArrayList<Alumno>();
        listadoGrupoB = new ArrayList<Alumno>();

        grupos = new ArrayList<String>();
        grupos.add("Grupo A");
        grupos.add("Grupo B");

        rutaFicheros = config.getServletContext().getRealPath("").concat(File.separator).concat("ficheros");

//        rutaFicheroGrupoA = config.getServletContext().getRealPath("").concat(File.separator).concat("ficheros")
//                .concat(File.separator).concat("2daw_a");
//        rutaFicheroGrupoB = config.getServletContext().getRealPath("").concat(File.separator).concat("ficheros")
//                .concat(File.separator).concat("2daw_b");

        listadoGrupoA = Utilidades.getAlumnos("2daw_a");
        listadoGrupoB = Utilidades.getAlumnos("2daw_b");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumnosControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnosControler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("grupo") == null || request.getParameter("grupo").equals("Grupo A")) {
            request.setAttribute("alumnos", listadoGrupoA);
            request.setAttribute("grupoSel", "2DAW A");
        } else {
            request.setAttribute("alumnos", listadoGrupoB);
            request.setAttribute("grupoSel", "2DAW B");
        }

        request.setAttribute("grupos", grupos);
        request.getRequestDispatcher("mostrarGrupo.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String grupo = (String) request.getParameter("grupo");

        ArrayList<Alumno> alumnosA = Utilidades.getAlumnos("2daw_a");
        ArrayList<Alumno> alumnosB = Utilidades.getAlumnos("2daw_a");
        ArrayList<Alumno> alumnosSalida = new ArrayList<Alumno>();

        for (int i = 1; i < 15; i++) {

            if (grupo.equalsIgnoreCase("2DAW A")) {
                if (request.getParameter(String.valueOf(i)) != null) {
                    alumnosSalida.add(alumnosA.get(i - 1));
                }
            }
            
            if (grupo.equalsIgnoreCase("2DAW B")) {
                if (request.getParameter(String.valueOf(i)) != null) {
                    alumnosSalida.add(alumnosB.get(i - 1));
                }
            }

        }

        request.setAttribute("grupo", grupo);
        request.setAttribute("alumnos", alumnosSalida);
        request.getRequestDispatcher("enviarMensaje.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
