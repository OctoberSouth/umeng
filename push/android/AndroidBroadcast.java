package com.live.util.push.android;

import com.live.util.push.AbstractAndroidNotification;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:06:43
 * @version 1.0
 * @describe
 */
public class AndroidBroadcast extends AbstractAndroidNotification {
	public AndroidBroadcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "broadcast");
	}
}
