package com.config;

import com.commom.JwtFilter;
import com.commom.ShiroFilter;
import com.commom.ShiroRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean factory(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置自定义的jwt过滤器
        Map<String, Filter> filterMap = new HashMap<String, Filter>();
        filterMap.put("jwt", new JwtFilter());// jwt验证
        filterMap.put("shiro", new ShiroFilter());// 不验证
        shiroFilterFactoryBean.setFilters(filterMap);
        // 设置无权限时跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/Error/unauthorized");
        // 设置过滤规则
        LinkedHashMap<String, String> filterRuleMap = new LinkedHashMap<String, String>();
        filterRuleMap.put("/user/userLogin", "shiro");
        filterRuleMap.put("/user/userRegister", "shiro");
        filterRuleMap.put("/blog/getBlogList/**", "shiro");
        filterRuleMap.put("/blog/getBlogListById/**", "shiro");
        filterRuleMap.put("/blog/getCommentList/**", "shiro");
        filterRuleMap.put("/druid/**", "shiro");
        filterRuleMap.put("/actuator/**", "shiro");
        filterRuleMap.put("/Error/**", "shiro");
        filterRuleMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
//        DefaultSessionManager sessionManager = new DefaultSessionManager();
//        sessionManager.setSessionValidationSchedulerEnabled(false);

        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(ShiroRealm());
        securityManager.setSubjectDAO(subjectDAO);
//        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroRealm ShiroRealm() {
        return new ShiroRealm();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


}
