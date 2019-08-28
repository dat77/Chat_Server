package ua.kiev.prog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
	private UserMap usrMap = UserMap.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//byte[] buf = requestBodyToArray(req);
        //String bufStr = new String(buf, StandardCharsets.UTF_8);

        if (req.getParameter("classname").equals("Message")) {
            //Message msg = Message.fromJSON(bufStr);
            Message msg = Message.fromJSON(req.getParameter("data"));
            if (msg != null)
                msgList.add(msg);
            else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (req.getParameter("classname").equals("User")) {
            User usr = User.fromJSON(req.getParameter("data"));
            if (usr != null)
                usrMap.add(usr);
            else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
	}

	private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
