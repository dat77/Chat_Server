package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "Handle", urlPatterns = {"/handle"})
public class HandlerServlet extends HttpServlet {

    private UserMap usrMap = UserMap.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usersStr = req.getParameter("users");
        String statusStr = req.getParameter("setstatus");
        String userName = req.getParameter("username");
        String json;
        if (usersStr != null) {
            switch (usersStr) {
                case "all":
                    json = usrMap.toJSON();
                    if (json != null) {
                        PrintWriter pw = resp.getWriter();
                        pw.print(json);
                    }
                    break;
                default:
                    json = usrMap.toJSON(usersStr);
                    if (json != null) {
                        PrintWriter pw = resp.getWriter();
                        pw.print(json);
                    }
            }
        }
        if (statusStr != null){
            User.Status newStatus;
            switch (statusStr){
                case "n": newStatus = User.Status.NOTAVAILABLE; break;
                case "d": newStatus = User.Status.DONOTDESTURB; break;
                case "h": newStatus = User.Status.HIDDEN; break;
                default: newStatus = User.Status.AVAILABLE;
            }
            usrMap.setStatus(userName, newStatus);
            json = usrMap.toJSON(userName);
            if (json != null) {
                PrintWriter pw = resp.getWriter();
                pw.print(json);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
