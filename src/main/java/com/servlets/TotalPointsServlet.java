package com.servlets;

import com.chess.MatchManager;
import com.chess.Tournament;
import com.chess.TournamentManager;
import com.database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="calculateTotalPoints",urlPatterns = "/calculateTotalPoints")
public class TotalPointsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roundNumber=Integer.parseInt(req.getParameter("roundNumber"));
        int tournamentId=Integer.parseInt(req.getParameter("tournamentID"));

        TournamentManager tournamentManager=new TournamentManager();
        Tournament tournament=tournamentManager.getTournamentDetails("select *from Tournament where TournamentId="+tournamentId).get(0);

        if(tournament.getRounds()==tournament.getRoundsCompleted()){
            if(tournament.getResult().equals("NO")){
                System.out.println(roundNumber);
                MatchManager matchManager1=new MatchManager();
                matchManager1.calculateTotalPoints(roundNumber,tournamentId);
                DatabaseConnection databaseConnection=new DatabaseConnection();
                databaseConnection.updateQuery("update Tournament set RESULT='YES' where TournamentId="+tournamentId);
                databaseConnection.closeConnection();
            }
        }else{
            MatchManager matchManager=new MatchManager();
            matchManager.calculateTotalPoints(roundNumber,tournamentId);
        }


    }
}
