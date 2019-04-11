package com.live.util.push.android;

import com.live.util.push.AbstractAndroidNotification;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:17
 * @version 1.0
 * @describe
 */
public class AndroidUnicast extends AbstractAndroidNotification {
	public AndroidUnicast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }

}