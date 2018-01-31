package org.example;

import src.main.java.nl.sogyo.mancala.NormalPit;
import src.main.java.nl.sogyo.mancala.Pit;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    private Pit rootPit;
    ArrayList<Integer> state = new ArrayList<Integer>();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        res.setStatus(HttpServletResponse.SC_OK);
        System.out.println("Hello Universe!");

        rootPit = new NormalPit();

        HttpSession session = req.getSession();
        session.setAttribute("pit", rootPit);

        RequestDispatcher rd = req.getRequestDispatcher("continuePlay.jsp");
        rd.forward(req, res);
    }
}
