package com.wanwu.panta.biz;

import java.util.List;

public interface IRoleService {
	public List<String> queryPantaRolesByUserId(int userId);
}
