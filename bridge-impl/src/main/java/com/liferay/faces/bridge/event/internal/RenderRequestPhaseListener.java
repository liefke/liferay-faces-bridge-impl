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
package com.liferay.faces.bridge.event.internal;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.portlet.faces.Bridge;

import com.liferay.faces.bridge.context.BridgeContext;


/**
 * Section 5.2.6 of the Spec indicates that the bridge must proactively ensure that only the RESTORE_VIEW phase
 * executes, and Section 6.4 indicates that a PhaseListener must be used.
 *
 * @author  Neil Griffin
 */
public class RenderRequestPhaseListener extends RenderRequestPhaseListenerCompat implements PhaseListener {

	// serialVersionUID
	private static final long serialVersionUID = 8470095938465172618L;

	// Protected (Lazy-Initialized) Constants
	protected static Boolean VIEW_PARAMETERS_ENABLED;

	// Private Data Members
	private PhaseId phaseId = PhaseId.RESTORE_VIEW;

	public void afterPhase(PhaseEvent phaseEvent) {

		BridgeContext bridgeContext = BridgeContext.getCurrentInstance();

		if (VIEW_PARAMETERS_ENABLED == null) {

			synchronized (phaseId) {

				if (VIEW_PARAMETERS_ENABLED == null) {
					VIEW_PARAMETERS_ENABLED = isViewParametersEnabled(bridgeContext);
				}
			}
		}

		// If the JSF 2 "View Parameters" feature is not enabled, then ensure that only the RESTORE_VIEW phase executes.
		if (!VIEW_PARAMETERS_ENABLED && (bridgeContext.getPortletRequestPhase() == Bridge.PortletPhase.RENDER_PHASE)) {
			phaseEvent.getFacesContext().renderResponse();
		}
	}

	public void beforePhase(PhaseEvent phaseEvent) {
		// This method is required by the PhaseListener interfaces but is not used.
	}

	public PhaseId getPhaseId() {
		return phaseId;
	}

}
