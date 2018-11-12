package com.clw.core.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * 1.配置ShiroFilterFactory 2.配置SecurityManager
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置shiro过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //1、定义shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2、设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //3、LinkedHashMap是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        //4、配置logout过滤器
        filterChainMap.put("/logout", "logout");

        //放行swagger
        filterChainMap.put("/swagger-ui.html","anon");
        filterChainMap.put("/swagger/**","anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/swagger-resources/**","anon");
        filterChainMap.put("/v2/**","anon");
        filterChainMap.put("/druid/**","anon");

        //5、所有url必须通过认证才可以访问
        filterChainMap.put("/**", "authc");



        //6、设置默认登录的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //7、设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //8、设置成功之后要跳转的链接
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //9、设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        return securityManager;
    }
}
