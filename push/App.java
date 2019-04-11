package com.live.util.push;

import com.github.pagehelper.util.StringUtil;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:08:15
 * @version 1.0
 * @describe
 */
public class App extends Thread {

	/**
	 * 推送消息
	 */
	private PushMessage message;

	/**
	 * 推送环境，true 正式环境，false测试环境
	 */
	private Boolean modelLive;

	/**
	 * 启动推送方式
	 * 
	 * @param message
	 */
	public App(PushMessage message, Boolean modelLive) {
		this.message = message;
		this.modelLive = modelLive;
	}

	@Override
	public void run() {
		if (message.getPushMemberType().equals(1)) {
			try {
				pushAll(message, modelLive);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Integer pushSingle = 2;
		if (message.getPushMemberType().equals(pushSingle)) {
			try {
				pushSingle(message, modelLive);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 广播发送到所有客户端
	 * 
	 * @param message
	 * @throws Exception
	 */
	public static boolean pushAll(PushMessage message, Boolean modelLive) throws Exception {
		Push p = new Push();
		p.sendIOSBroadcast(message, modelLive);
		p.sendAndroidBroadcast(message, modelLive);
		return true;
	}

	private static Integer iosType = 2;

	private static Integer androidType = 1;

	/**
	 * 向其中一个会员客户端进行消息推送
	 * 
	 * @param message
	 * @param modelLive
	 * @throws Exception
	 */
	public static boolean pushSingle(PushMessage message, Boolean modelLive) throws Exception {
		Push p = new Push();
		if (iosType.equals(message.getDeviceType())) {
			if (StringUtil.isNotEmpty(message.getUmengPushId())) {
				return p.sendIOSUnicast(message, message.getUmengPushId(), modelLive);
			}
		}
		if (androidType.equals(message.getDeviceType())) {
			if (StringUtil.isNotEmpty(message.getUmengPushId())) {
				return p.sendAndroidUnicast(message, message.getUmengPushId(), modelLive);
			}
		}
		return false;
	}

}
