package cybersoft.javabackend.crm.filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodeFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		((HttpServletRequest) request).setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		((HttpServletResponse) response).setCharacterEncoding("UTF-8");
	}
}
