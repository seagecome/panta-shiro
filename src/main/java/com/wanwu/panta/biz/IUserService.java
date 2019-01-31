package com.wanwu.panta.biz;

import com.wanwu.panta.dal.domain.PantaUser;

public interface IUserService {
	public PantaUser getPantaUserById(int userId);
	public PantaUser getPantaUserByUsername(String username);
}
