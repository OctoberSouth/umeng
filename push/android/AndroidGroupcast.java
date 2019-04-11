package com.live.util.push.android;

import org.json.JSONObject;

import com.live.util.push.AbstractAndroidNotification;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:10
 * @version 1.0
 * @describe
 */
public class AndroidGroupcast extends AbstractAndroidNotification {
	public AndroidGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
