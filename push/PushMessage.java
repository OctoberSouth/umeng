package com.live.util.push;

/**
 * 发送消息类
 * 
 * @author 刘攀
 * @time 2019年4月11日上午11:44:44
 * @version 1.0
 * @describe
 */
public class PushMessage {

	/**
	 * id
	 */
	private String id;

	/**
	 * 安卓广播使用
	 */
	private String ticker = "ticker";

	/**
	 * 标题
	 */
	private String title = "";

	/**
	 * 通知内容
	 */
	private String content;

	/**
	 * 机型
	 */
	private Integer deviceType;

	/**
	 * 推送ID
	 */
	private String umengPushId;

	/**
	 * 推送方式，1：广播，2，个人
	 */
	private Integer pushMemberType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getUmengPushId() {
		return umengPushId;
	}

	public void setUmengPushId(String umengPushId) {
		this.umengPushId = umengPushId;
	}

	public Integer getPushMemberType() {
		return pushMemberType;
	}

	public void setPushMemberType(Integer pushMemberType) {
		this.pushMemberType = pushMemberType;
	}



}
