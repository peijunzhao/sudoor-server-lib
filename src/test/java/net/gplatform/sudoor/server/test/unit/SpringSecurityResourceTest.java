package net.gplatform.sudoor.server.test.unit;

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

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.gplatform.sudoor.server.Application;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class SpringSecurityResourceTest {
	@Autowired
	TestUtils testUtils;

	public static Client client;

	@BeforeClass
	public static void init() {
		client = ClientBuilder.newBuilder().build();
		client.register(JacksonJsonProvider.class);
	}

	/**
	 * Post login can not be tested here as we can not send the cookie
	 */
	@Test
	public void testSpringSecurityAuthenticationPreLogin() {
		WebTarget target = client.target(testUtils.getEmbeddedServletContainerBaseURL() + "/data/ws/rest").path(
				"/sudoor/SpringSecurity/Authentication");
		Response response = target.request(MediaType.WILDCARD_TYPE).get();
		int statusCode = response.getStatus();
		assert (statusCode == 200);
		
		Map result = response.readEntity(Map.class);
		assert ("anonymousUser".equals(result.get("principal")));
	}

}
