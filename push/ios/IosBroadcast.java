package com.live.util.push.ios;

import com.live.util.push.AbstractIosNotification;
import com.live.util.push.PushConstant;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:29
 * @version 1.0
 * @describe
 */
public class IosBroadcast extends AbstractIosNotification {
	public IosBroadcast() throws Exception {
		setAppMasterSecret(PushConstant.SAPP_MASTER_SECRET);
		setPredefinedKeyValue("appkey", PushConstant.APP_KEY);
		this.setPredefinedKeyValue("type", "broadcast");
	}
}
