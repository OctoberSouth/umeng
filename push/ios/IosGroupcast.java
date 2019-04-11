package com.live.util.push.ios;

import org.json.JSONObject;

import com.live.util.push.AbstractIosNotification;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:49
 * @version 1.0
 * @describe
 */
public class IosGroupcast extends AbstractIosNotification {
	public IosGroupcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
