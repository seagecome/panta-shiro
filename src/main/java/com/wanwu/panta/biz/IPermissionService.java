package com.wanwu.panta.biz;

import java.util.List;

public interface IPermissionService {
	public List<String> queryPermissionByUserId(int userId);
}
