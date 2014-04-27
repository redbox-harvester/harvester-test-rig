/*******************************************************************************
 * Copyright (C) 2013 Queensland Cyber Infrastructure Foundation (http://www.qcif.edu.au/)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package au.com.redboxresearchdata.harvester.testrig.runner;

import groovy.util.ConfigObject;

import java.util.Map;

import org.apache.activemq.broker.BrokerService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

import au.com.redboxresearchdata.util.config.Config;

/**
 * Starts the Spring Context and will initialize the Spring Integration routes.
 * Defaults are offered for config file, if missing from command line and
 * applicationContext if missing from config. Config.getConfig is used to
 * process config file.
 * 
 * @author Matt Mulholland
 * @since 1.0
 * 
 */
public final class Console {

	private static final Logger LOG = Logger.getLogger(Console.class);
	private static final String CONFIG_FILE_PATH_DEFAULT = "config.groovy";
	private static final String CONTEXT_DEFAULT = "applicationContext-si.xml";

	private Console() {
		throw new UnsupportedOperationException("No Instances allowed of console runner.");
	}

	@SuppressWarnings("rawtypes")
	public static void main(final String[] args) {

        Map configMap = getConfigMap(args);
		String contextPath = StringUtils.defaultIfEmpty((String) configMap.get("file.siPath"), CONTEXT_DEFAULT);

		final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load(contextPath);
		context.refresh();
		context.registerShutdownHook();
		run(context);
	}

	@SuppressWarnings("rawtypes")
	public static Map getConfigMap(String[] args) {
		String configFilePath = CONFIG_FILE_PATH_DEFAULT;
		if (!(ArrayUtils.isEmpty(args)) && StringUtils.isNotBlank(args[0])) {
			configFilePath = args[0];
		}
		String environment = System.getProperty("environment", "development");

		ConfigObject config = Config.getConfig(environment, configFilePath);
		LOG.debug("Config object captured: ");
		LOG.debug(config);
		Map configMap = config.flatten();
		// TODO : find neater way - these system properties setters needed for
		// applicationContext to find property values
		System.setProperty("environment", environment);
		System.setProperty("runtime.config.file", (String) configMap.get("file.runtimePath"));
		return configMap;
	}

	public static void run(GenericXmlApplicationContext context) {
		int exitStatus = -1;
		try {
			while (context.isRunning()) {
				Thread.sleep(1000);
			}
			exitStatus = 0;
		} catch (Exception e) {
			LOG.warn("Interrupted, shutting down...");
            exitStatus = 1;
		} finally {
			LOG.info("Exiting application...bye.");
			System.exit(exitStatus);
		}
	}
}
