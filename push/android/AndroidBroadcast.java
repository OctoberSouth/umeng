package com.live.util.push.android;

import com.live.util.push.AbstractAndroidNotification;
import com.live.util.push.PushConstant;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:06:43
 * @version 1.0
 * @describe
 */
public class AndroidBroadcast extends AbstractAndroidNotification {
	public AndroidBroadcast() throws Exception {
		setAppMasterSecret(PushConstant.SAPP_MASTER_SECRET);
		setPredefinedKeyValue("appkey", PushConstant.APP_KEY);
		this.setPredefinedKeyValue("type", "broadcast");
	}
}
