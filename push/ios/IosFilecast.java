package com.live.util.push.ios;

import com.live.util.push.AbstractIosNotification;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:45
 * @version 1.0
 * @describe
 */
public class IosFilecast extends AbstractIosNotification {
	public IosFilecast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "filecast");	
	}
	
	public void setFileId(String fileId) throws Exception {
    	setPredefinedKeyValue("file_id", fileId);
    }
}
