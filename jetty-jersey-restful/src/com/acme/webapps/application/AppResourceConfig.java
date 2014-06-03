package com.acme.webapps.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.webapps.services.resources.ApplicationCommandResource;

public class AppResourceConfig extends ResourceConfig {

	private Logger log = LoggerFactory.getLogger(getClass());

	public AppResourceConfig() {

		// list of packages containing annotated resources
		register(ApplicationCommandResource.class);
		if (log.isDebugEnabled()) {
			log.debug(" Configured App Resources" + getResources());
		}
		// packages("com.acme.webapps.services.resources;com.acme.webapps.services.other");
	}

}
