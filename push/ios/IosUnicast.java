package com.live.util.push.ios;

import com.live.util.push.AbstractIosNotification;
import com.live.util.push.PushConstant;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:58
 * @version 1.0
 * @describe
 */
public class IosUnicast extends AbstractIosNotification {
	public IosUnicast() throws Exception {
		setAppMasterSecret(PushConstant.SAPP_MASTER_SECRET);
		setPredefinedKeyValue("appkey", PushConstant.APP_KEY);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }
}
