package net.gplatform.sudoor.server.integration;

/*
 * #%L
 * sudoor-server-lib
 * %%
 * Copyright (C) 2013 - 2015 Shark Xu
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class DefaultEventServiceActivator {
	final Logger logger = LoggerFactory.getLogger(DefaultEventServiceActivator.class);

	@ServiceActivator(inputChannel = "eventPublishChannel")
	public void handle(Object event) throws Exception {
		logger.debug("Get Event: {} @ {}", event, new Date());
	}
	
	@ServiceActivator(inputChannel = "asyncEventPublishChannel")
	public void handleAsyncEvent(Object event) throws Exception {
		logger.debug("Get Asnyc Event: {} @ {}", event, new Date());
	}

}
