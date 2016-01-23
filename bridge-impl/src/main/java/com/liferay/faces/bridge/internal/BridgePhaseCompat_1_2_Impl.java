/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.bridge.internal;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;

import com.liferay.faces.bridge.config.BridgeConfig;
import com.liferay.faces.bridge.context.BridgeContext;


/**
 * This class provides a compatibility layer that isolates differences related to JSF 1.2.
 *
 * @author  Neil Griffin
 */
public abstract class BridgePhaseCompat_1_2_Impl extends BridgePhaseBaseImpl {

	public BridgePhaseCompat_1_2_Impl(PortletConfig portletConfig, BridgeConfig bridgeConfig) {
		super(portletConfig, bridgeConfig);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void removeBridgeContextAttribute(PortletRequest portletRequest) {
		portletRequest.removeAttribute(BridgeExt.BRIDGE_CONTEXT_ATTRIBUTE);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void setBridgeContextAttribute(PortletRequest portletRequest, BridgeContext bridgeContext) {
		portletRequest.setAttribute(BridgeExt.BRIDGE_CONTEXT_ATTRIBUTE, bridgeContext);
	}
}
