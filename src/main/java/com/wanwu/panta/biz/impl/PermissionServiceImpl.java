package com.wanwu.panta.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanwu.panta.biz.IPermissionService;
import com.wanwu.panta.dal.mapper.PantaPermissionMapper;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PantaPermissionMapper pantaPermissionMapper;
	
	@Override
	public List<String> queryPermissionByUserId(int userId) {
		return pantaPermissionMapper.queryPermissionByUserId(userId);
	}

}
