package com.acme.webapps.main;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import com.acme.webapps.application.AppResourceConfig;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {

		// http://repo1.maven.org/maven2/org/glassfish/jersey/containers/jersey-container-jetty-http/2.9/

		URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080)
				.build();
		AppResourceConfig config = new AppResourceConfig();

		Server server = JettyHttpContainerFactory.createServer(baseUri, config);

		server.start();
		server.join();

	}

}
