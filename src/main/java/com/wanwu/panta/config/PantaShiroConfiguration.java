package com.wanwu.panta.config;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;

import com.wanwu.panta.manager.PantaShiroRealm;

@Configuration
public class PantaShiroConfiguration {
	@Bean
	public PantaShiroRealm pantaShiroRealm() {
		PantaShiroRealm pantaShiroRealm = new PantaShiroRealm();
		return pantaShiroRealm;
	}
	
	@Bean
	public SecurityManager securityManager() {
		WebSecurityManager securityManager = new DefaultWebSecurityManager(pantaShiroRealm());
		return securityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean pantaShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/boot/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/login/check", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setLoginUrl("/login.page");
		shiroFilterFactoryBean.setSuccessUrl("/loginSuccess.page");
		shiroFilterFactoryBean.setUnauthorizedUrl("/accessDenied.page");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor pantaAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator app=new DefaultAdvisorAutoProxyCreator();
	    app.setProxyTargetClass(true);
	    return app;

	}
}
