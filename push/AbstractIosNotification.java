package com.live.util.push;

import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONObject;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:08:38
 * @version 1.0
 * @describe
 */
public abstract class AbstractIosNotification extends AbstractUmengNotification {

	/**
	 * Keys can be set in the aps level
	 */
	protected static final HashSet<String> APS_KEYS = new HashSet<String>(Arrays.asList(new String[]{
			"alert", "badge", "sound", "content-available"
	}));
	
	@Override
	public boolean setPredefinedKeyValue(String key, Object value) throws Exception {
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);
		} else if (APS_KEYS.contains(key)) {
			// This key should be in the aps level
			JSONObject apsJson = null;
			JSONObject payloadJson = null;
			if (rootJson.has(PushConstant.PAY_LOAD)) {
				payloadJson = rootJson.getJSONObject(PushConstant.PAY_LOAD);
			} else {
				payloadJson = new JSONObject();
				rootJson.put(PushConstant.PAY_LOAD, payloadJson);
			}
			if (payloadJson.has(PushConstant.APS)) {
				apsJson = payloadJson.getJSONObject(PushConstant.APS);
			} else {
				apsJson = new JSONObject();
				payloadJson.put(PushConstant.APS, apsJson);
			}
			apsJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson = null;
			if (rootJson.has(PushConstant.POLICY)) {
				policyJson = rootJson.getJSONObject(PushConstant.POLICY);
			} else {
				policyJson = new JSONObject();
				rootJson.put(PushConstant.POLICY, policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (key.equals(PushConstant.PAY_LOAD) || key.equals(PushConstant.APS) || key.equals(PushConstant.POLICY)) {
				throw new Exception("You don't need to set value for " + key + " , just set values for the sub keys in it.");
			} else {
				throw new Exception("Unknownd key: " + key);
			}
		}
		
		return true;
	}

	/**
	 * Set customized key/value for IOS notification
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:26:57
	 * @version 1.0
	 * @describe
	 */
	public boolean setCustomizedField(String key, String value) throws Exception {
		JSONObject payloadJson = null;
		if (rootJson.has(PushConstant.PAY_LOAD)) {
			payloadJson = rootJson.getJSONObject(PushConstant.PAY_LOAD);
		} else {
			payloadJson = new JSONObject();
			rootJson.put(PushConstant.PAY_LOAD, payloadJson);
		}
		payloadJson.put(key, value);
		return true;
	}

	public void setAlert(String token) throws Exception {
    	setPredefinedKeyValue("alert", token);
    }
	
	public void setBadge(Integer badge) throws Exception {
    	setPredefinedKeyValue("badge", badge);
    }
	
	public void setSound(String sound) throws Exception {
    	setPredefinedKeyValue("sound", sound);
    }
	
	public void setContentAvailable(Integer contentAvailable) throws Exception {
    	setPredefinedKeyValue("content-available", contentAvailable);
    }
}
