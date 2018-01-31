package org.example;

import src.main.java.nl.sogyo.mancala.NormalPit;
import src.main.java.nl.sogyo.mancala.Pit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ContinuePlay extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Pit rootPit = (NormalPit) session.getAttribute("pit");

        session.setAttribute("pit", rootPit);

        RequestDispatcher rd = req.getRequestDispatcher("continuePlay.jsp");
        rd.forward(req, resp);
    }
}
