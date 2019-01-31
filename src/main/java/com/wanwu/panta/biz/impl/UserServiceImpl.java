package com.wanwu.panta.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanwu.panta.biz.IUserService;
import com.wanwu.panta.dal.domain.PantaUser;
import com.wanwu.panta.dal.domain.PantaUserExample;
import com.wanwu.panta.dal.mapper.PantaUserMapper;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private PantaUserMapper pantaUserMapper;
	
	@Override
	public PantaUser getPantaUserById(int userId) {
		PantaUserExample example = new PantaUserExample();
		example.createCriteria().andIdEqualTo(userId);
		List<PantaUser> puList = pantaUserMapper.selectByExample(example);
		if(puList == null || puList.size() == 0) {
			return null;
		}
		return puList.get(0);
	}

	@Override
	public PantaUser getPantaUserByUsername(String username) {
		PantaUserExample example = new PantaUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<PantaUser> puList = pantaUserMapper.selectByExample(example);
		if(puList == null || puList.size() == 0) {
			return null;
		}
		return puList.get(0);
	}

}
