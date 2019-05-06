package com.crm.projectmanage.util;

import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;

/**
 * @author 金海洋
 * @date 2019/5/5  -14:14
 */
public class getLoginMessage {

	/**
	 * 获取当前登陆人的ID
	 * @return
	 */
	public String getBaseUserId() {
		String user = ResourceUtil.getSessionUser().getId();
		return user;
	}



}
