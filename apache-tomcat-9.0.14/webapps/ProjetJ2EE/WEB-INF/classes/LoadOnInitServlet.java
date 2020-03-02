import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mediatek2020.Mediatheque;
import persistantdata.MediathequeData;


@WebServlet (urlPatterns="/initializeResources", loadOnStartup=1)
public class LoadOnInitServlet extends HttpServlet {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		System.out.println("******************************************************************");
	}

}
