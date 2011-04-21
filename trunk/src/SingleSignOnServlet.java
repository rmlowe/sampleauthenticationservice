import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public final class SingleSignOnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected final void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException,
			ServletException {
		final String salt = request.getParameter("salt");
		final String userId = (String) request.getSession().getAttribute(
				"userId");

		if (userId == null) {
			request.setAttribute("salt", salt);
			request.setAttribute("error", Boolean.FALSE);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/signin.jsp")
					.forward(request, response);
		} else {
			final byte[] saltBytes = Base64.decodeBase64(salt);
			final String key = this.getServletContext().getInitParameter("key");

			try {
				final MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(userId.getBytes("UTF-8"));
				md.update(key.getBytes("UTF-8"));
				md.update(saltBytes);

				// Trim the string since Commons Codec appends CRLF
				final String digest = Base64.encodeBase64String(md.digest())
						.trim();

				final String url = this.getServletContext().getInitParameter(
						"ekpBase")
						+ "servlet/ekp/authenticationTokenVerifier?userId="
						+ URLEncoder.encode(userId, "UTF-8")
						+ "&digest="
						+ URLEncoder.encode(digest, "UTF-8");
				response.sendRedirect(url);
			} catch (final NoSuchAlgorithmException e) {
				throw new AssertionError(
						"Required message digest algorithm MD5 unsupported");
			}
		}
	}

	protected final void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException,
			ServletException {
		doGet(request, response);
	}
}
