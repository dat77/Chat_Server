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
        String json;
        if (usersStr != null) {
            switch (usersStr) {
                case "all":
                    json = usrMap.toJSON();
                    if (json != null) {
//                        OutputStream os = resp.getOutputStream();
//                        byte[] buf = json.getBytes(StandardCharsets.UTF_8);
//                        os.write(buf);

                        PrintWriter pw = resp.getWriter();
                        pw.print(json);
                    }
                    break;
                default:
                    json = usrMap.toJSON(usersStr);
                    if (json != null) {
//                        OutputStream os = resp.getOutputStream();
//                        byte[] buf = json.getBytes(StandardCharsets.UTF_8);
//                        os.write(buf);

                        PrintWriter pw = resp.getWriter();
                        pw.print(json);
                    }

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
