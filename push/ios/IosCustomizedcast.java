package com.live.util.push.ios;

import com.live.util.push.AbstractIosNotification;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:07:37
 * @version 1.0
 * @describe
 */
public class IosCustomizedcast extends AbstractIosNotification {
	public IosCustomizedcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "customizedcast");	
	}
	
	public void setAlias(String alias,String aliasType) throws Exception {
    	setPredefinedKeyValue("alias", alias);
    	setPredefinedKeyValue("alias_type", aliasType);
    }
		
	public void setFileId(String fileId, String aliasType) throws Exception {
		setPredefinedKeyValue("file_id", fileId);
		setPredefinedKeyValue("alias_type", aliasType);
	}

}
