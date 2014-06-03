package com.acme.webapps.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {

		// http://repo1.maven.org/maven2/org/glassfish/jersey/containers/jersey-container-jetty-http/2.9/

		// URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080)
		// .build();
		// AppResourceConfig config = new AppResourceConfig();

		ServletHolder servletHolder = new ServletHolder(
				new HttpServletDispatcher());
		servletHolder
				.setInitParameter("javax.ws.rs.Application",
						"com.acme.webapps.application.AppResourceConfig");

		ServletContextHandler servletCtxHandler = new ServletContextHandler();
		servletCtxHandler.addServlet(servletHolder, "/");
		
		

		Server server = new Server(8080);
		server.setHandler(servletCtxHandler);
		server.start();
		server.join();

	}
}
