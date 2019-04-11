package com.live.util.push;

import com.live.util.push.android.AndroidBroadcast;
import com.live.util.push.android.AndroidUnicast;
import com.live.util.push.ios.IosBroadcast;
import com.live.util.push.ios.IosUnicast;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午11:46:11
 * @version 1.0
 * @describe
 */
public class Push {

	private PushClient client = new PushClient();

	/**
	 * 安卓广播
	 * 
	 * @param message
	 * @throws Exception
	 */
	public boolean sendAndroidBroadcast(PushMessage message, Boolean modelLive) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast();
		broadcast.setTicker(message.getTicker());
		broadcast.setTitle(message.getTitle());
		broadcast.setText(message.getContent());
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AbstractAndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		if (modelLive) {
			broadcast.setProductionMode();
		} else {
			broadcast.setTestMode();
		}
		return client.send(broadcast);
	}

	/**
	 * 安卓指定推送
	 * 
	 * @param message
	 * @param tokens
	 * @throws Exception
	 */
	public boolean sendAndroidUnicast(PushMessage message, String tokens, Boolean modelLive) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast();
		// TODO Set your device token
		unicast.setDeviceToken(tokens);
		unicast.setTicker(message.getContent());
		unicast.setTitle(message.getTitle());
		unicast.setText(message.getContent());
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AbstractAndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		if (modelLive) {
			unicast.setProductionMode();
		} else {
			unicast.setTestMode();
		}
		return client.send(unicast);
	}

	/**
	 * 苹果广播
	 * 
	 * @param message
	 * @throws Exception
	 */

	public boolean sendIOSBroadcast(PushMessage message, Boolean modelLive) throws Exception {
		IosBroadcast broadcast = new IosBroadcast();
		broadcast.setAlert(message.getContent());
		broadcast.setBadge(0);
		broadcast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		if (modelLive) {
			broadcast.setProductionMode();
		} else {
			broadcast.setTestMode();
		}
		return client.send(broadcast);
	}

	/**
	 * 苹果指定推送
	 * 
	 * @param message
	 * @param tokens
	 * @throws Exception
	 */
	public boolean sendIOSUnicast(PushMessage message, String tokens, Boolean modelLive) throws Exception {
		IosUnicast unicast = new IosUnicast();
		// TODO Set your device token
		unicast.setDeviceToken(tokens);
		unicast.setAlert(message.getContent());
		unicast.setBadge(0);
		unicast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		if (modelLive) {
			unicast.setProductionMode();
		} else {
			unicast.setTestMode();
		}
		return client.send(unicast);
	}

	/**
	 * 安卓透传
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public boolean sendAndroidTransmission(String token, String key, String value, Boolean modelLive) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast();
		unicast.setCustomField(value);
		unicast.setDeviceToken(token);
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AbstractAndroidNotification.DisplayType.MESSAGE);
		if (modelLive) {
			unicast.setProductionMode();
		} else {
			unicast.setTestMode();
		}
		unicast.setExtraField(key, value);
		return client.send(unicast);
	}

	/**
	 * 苹果透传
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public boolean sendIosTransmission(String token, String key, String value, Boolean modelLive) throws Exception {
		IosUnicast unicast = new IosUnicast();
		unicast.setDeviceToken(token);
		unicast.setContentAvailable(1);
		if (modelLive) {
			unicast.setProductionMode();
		} else {
			unicast.setTestMode();
		}
		unicast.setCustomizedField(key, value);
		return client.send(unicast);
	}

}
