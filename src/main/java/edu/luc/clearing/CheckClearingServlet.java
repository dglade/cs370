package edu.luc.clearing;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CheckClearingServlet extends HttpServlet {
	
	private RequestReader requestReader;
	private CheckHistory checkHistory;
	
	public CheckClearingServlet() {
		requestReader = new RequestReader();
		checkHistory = new CheckHistory(new DataStoreAdapter());
	}
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().print(requestReader.respond(req.getReader()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	resp.getWriter().print(checkHistory.getAmounts());
    }

}