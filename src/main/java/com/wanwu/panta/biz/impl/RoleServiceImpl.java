package com.wanwu.panta.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanwu.panta.biz.IRoleService;
import com.wanwu.panta.dal.mapper.PantaRoleMapper;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private PantaRoleMapper pantaRoleMapper;
	
	@Override
	public List<String> queryPantaRolesByUserId(int userId) {
		return pantaRoleMapper.queryPantaRolesByUserId(userId);
	}

}
