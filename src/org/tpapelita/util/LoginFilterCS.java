package org.tpapelita.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilterCS implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException, IllegalStateException {
		LoginController loginController = (LoginController) ((HttpServletRequest) request)
				.getSession().getAttribute("LoginController");

		if (loginController == null || !loginController.getIsLoggedIn()) {
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/cs/login.jsf");
		}
		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}

