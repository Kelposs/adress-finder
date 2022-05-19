
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HRSearchServlet
 */
@WebServlet("/hrsearch")
public class HRSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HRSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hashCode = request.getParameter("area");
		HttpSession session = request.getSession();
		if (hashCode == null) {
			session.removeAttribute("result");
		} else {
			List<Area> selected = (List<Area>) session.getAttribute("result");
			for (int i = 0; i < selected.size(); i++) {
				if (selected.get(i).hashCode() == Integer.parseInt(hashCode)) {
					request.setAttribute("selected", selected.get(i));
				}
			}
		}
		request.getRequestDispatcher("/WEB-INF/hrsearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("key");

		Response result = HRjson.search(key);
		List<Area> location = result.getLocation();

		HttpSession session = request.getSession();
		session.setAttribute("result", location);
		request.getRequestDispatcher("/WEB-INF/hrsearch.jsp").forward(request, response);
	}

}
