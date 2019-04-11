package com.live.util.push;

import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONObject;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:08:59
 * @version 1.0
 * @describe
 */
public abstract class AbstractUmengNotification {

	protected String payLoad = "payload";

	protected String body = "body";

	protected String policy = "policy";

	protected String extra = "extra";

	protected String aps = "aps";

	/**
	 * This JSONObject is used for constructing the whole request string.
	 */
	protected final JSONObject rootJson = new JSONObject();
	
	
	/**
	 * The app master secret
	 */
	protected String appMasterSecret;
	
	/**
	 * Keys can be set in the root level
	 */
	protected static final HashSet<String> ROOT_KEYS = new HashSet<String>(Arrays.asList(new String[]{
			"appkey", "timestamp", "type", "device_tokens", "alias", "alias_type", "file_id", 
			"filter", "production_mode", "feedback", "description", "thirdparty_id"}));
	
	/**
	 * Keys can be set in the policy level
	 */
	protected static final HashSet<String> POLICY_KEYS = new HashSet<String>(Arrays.asList(new String[]{
			"start_time", "expire_time", "max_send_num"
	}));
	
	/**
	 * Set predefined keys in the rootJson, for extra keys(Android) or customized
	 * keys(IOS) please refer to corresponding methods in the subclass.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:27:34
	 * @version 1.0
	 * @describe
	 */
	public abstract boolean setPredefinedKeyValue(String key, Object value) throws Exception;
	public void setAppMasterSecret(String secret) {
		appMasterSecret = secret;
	}
	
	public String getPostBody(){
		return rootJson.toString();
	}
	
	protected final String getAppMasterSecret(){
		return appMasterSecret;
	}
	
	protected void setProductionMode(Boolean prod) throws Exception {
    	setPredefinedKeyValue("production_mode", prod.toString());
    }

	/**
	 * 正式模式
	 * 
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:32:11
	 * @version 1.0
	 * @describe
	 */
    public void setProductionMode() throws Exception {
    	setProductionMode(true);
    }

	/**
	 * 测试模式
	 * 
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:32:16
	 * @version 1.0
	 * @describe
	 */
    public void setTestMode() throws Exception {
    	setProductionMode(false);
    }

	/**
	 * 发送消息描述，建议填写。
	 * 
	 * @param description
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:32:21
	 * @version 1.0
	 * @describe
	 */
    public void setDescription(String description) throws Exception {
    	setPredefinedKeyValue("description", description);
    }

	/**
	 * 定时发送时间，若不填写表示立即发送。格式: "YYYY-MM-DD hh:mm:ss"。
	 * 
	 * @param startTime
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:32:25
	 * @version 1.0
	 * @describe
	 */
    public void setStartTime(String startTime) throws Exception {
    	setPredefinedKeyValue("start_time", startTime);
    }

	/**
	 * 消息过期时间,格式: "YYYY-MM-DD hh:mm:ss"。
	 * 
	 * @param expireTime
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:32:28
	 * @version 1.0
	 * @describe
	 */
    public void setExpireTime(String expireTime) throws Exception {
    	setPredefinedKeyValue("expire_time", expireTime);
    }

	/**
	 * 发送限速，每秒发送的最大条数。
	 * 
	 * @param num
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:32:33
	 * @version 1.0
	 * @describe
	 */
    public void setMaxSendNum(Integer num) throws Exception {
    	setPredefinedKeyValue("max_send_num", num);
    }

}