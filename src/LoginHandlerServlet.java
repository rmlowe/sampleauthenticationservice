import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginHandlerServlet() {
	}

	protected final void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final String salt = request.getParameter("salt");
		final String userId = request.getParameter("userid");
		final String password = request.getParameter("password");

		if ((userId.equals("joestudent") && password.equals("topsecret"))
				|| (userId.equals("marylearner") && password
						.equals("try2guess"))) {
			request.getSession().setAttribute("userId", userId);
			response.sendRedirect("sso?salt="
					+ URLEncoder.encode(salt, "UTF-8"));
		} else {
			request.setAttribute("salt", salt);
			request.setAttribute("error", Boolean.TRUE);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/signin.jsp")
					.forward(request, response);
		}
	}
}
