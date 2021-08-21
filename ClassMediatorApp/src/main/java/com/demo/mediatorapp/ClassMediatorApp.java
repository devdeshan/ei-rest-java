package com.demo.mediatorapp;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.json.JSONObject;

public class ClassMediatorApp extends AbstractMediator {

	public boolean mediate(MessageContext context) {
		try {
			org.apache.axis2.context.MessageContext axis2MessageContext = ((Axis2MessageContext) context)
					.getAxis2MessageContext();

			// extract request json
			JSONObject jsonObject = new JSONObject(JsonUtil.jsonPayloadToString(axis2MessageContext));

			JSONObject responseObject = new JSONObject();

			if (jsonObject.has("demo")) {
				responseObject.put("demo", "success");
			} else {
				responseObject.put("error", "invalid input");
			}

			// set response json
			JsonUtil.getNewJsonPayload(axis2MessageContext, responseObject.toString(), true, true);
		} catch (Exception ex) {

		}
		return true;
	}
}
