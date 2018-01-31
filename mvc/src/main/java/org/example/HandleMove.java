package org.example;

import src.main.java.nl.sogyo.mancala.NormalPit;
import src.main.java.nl.sogyo.mancala.Pit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleMove extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Pit rootPit = (NormalPit) session.getAttribute("pit");
        String playPit = null;

        if (req.getParameterNames().hasMoreElements()){
            playPit = req.getParameterNames().nextElement();
        }

        System.out.println( Integer.parseInt(playPit) );

        rootPit.move(Integer.parseInt(playPit)).startMove();

        session.setAttribute("pit", rootPit);

        RequestDispatcher rd = req.getRequestDispatcher("continuePlay.jsp");
        rd.forward(req, resp);
    }
}
