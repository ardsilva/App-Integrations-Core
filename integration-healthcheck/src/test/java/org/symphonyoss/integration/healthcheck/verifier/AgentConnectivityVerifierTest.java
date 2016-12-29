/**
 * Copyright 2016-2017 Symphony Integrations - Symphony LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.symphonyoss.integration.healthcheck.verifier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.symphonyoss.integration.IntegrationPropertiesReader;
import org.symphonyoss.integration.model.ConnectionInfo;
import org.symphonyoss.integration.model.IntegrationProperties;

/**
 * Test class to validate {@link AgentConnectivityVerifier}
 * Created by rsanchez on 23/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AgentConnectivityVerifierTest {

  private static final String MOCK_HOST = "test.symphony.com";

  private static final String MOCK_PORT = "8444";

  @Mock
  private IntegrationPropertiesReader propertiesReader;

  @InjectMocks
  private AgentConnectivityVerifier verifier = new AgentConnectivityVerifier();

  @Test
  public void testHealthCheckUrl() {
    ConnectionInfo agent = new ConnectionInfo();
    agent.setHost(MOCK_HOST);
    agent.setPort(MOCK_PORT);

    IntegrationProperties properties = new IntegrationProperties();
    properties.setAgent(agent);

    doReturn(properties).when(propertiesReader).getProperties();

    assertEquals("https://test.symphony.com:8444/agent/v1/HealthCheck",
        verifier.getHealthCheckUrl());
  }

}