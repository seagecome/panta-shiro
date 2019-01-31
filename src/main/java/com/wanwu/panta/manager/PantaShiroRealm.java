package com.wanwu.panta.manager;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanwu.panta.biz.IPermissionService;
import com.wanwu.panta.biz.IRoleService;
import com.wanwu.panta.biz.IUserService;
import com.wanwu.panta.dal.domain.PantaUser;

public class PantaShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		PantaUser pu = userService.getPantaUserByUsername(username);
		if(pu == null) {
			return null;
		}
		List<String> prList = roleService.queryPantaRolesByUserId(pu.getId());
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(prList);
		
		List<String> ppList = permissionService.queryPermissionByUserId(pu.getId());
		simpleAuthorizationInfo.addStringPermissions(ppList);
		
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token.getPrincipal() == null) {
            return null;
        }
		String username = (String)token.getPrincipal();
		PantaUser pu = userService.getPantaUserByUsername(username);
		if(pu == null) {
			return null;
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, pu.getPassword(), getName());
		return simpleAuthenticationInfo;
	}

}
