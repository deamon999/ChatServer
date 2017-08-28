package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginServlet extends HttpServlet {
    private final String PASSWORD = "admin";
    private LoginList list = LoginList.getLoginList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestToArray(req);
        String bufStr = new String(buf);

        String[] temp = bufStr.split(",");
        String login = temp[0];
        String password = temp[1];
        if(password.equals("delete")){
            list.getList().remove(login);
        }
        if (password.equals(PASSWORD))
            list.add(login);
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private byte[] requestToArray(HttpServletRequest req) throws IOException {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        for(String s : list.getList()){
            sb.append(s).append(",");
        }
        String s = sb.toString();
        byte [] temp = s.getBytes();
        OutputStream os = resp.getOutputStream();
        os.write(temp);
    }
}
