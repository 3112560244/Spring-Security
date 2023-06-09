package com.qx.config;

import com.qx.filter.JwtAuthenticationTokenFilter;
import com.qx.handler.AccessDeniedHandlerImpl;
import com.qx.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //注入jwt过滤器
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //注入自定义的认证入口点
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    //注入自定义的权限不足处理器
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    /**
     * 创建BCryptPasswordEncoder注入容器
     * 没加之前支持数据库明文密码  {noop}test
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //配置AuthenticationManager  使用默认的鉴权管理器
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //配置HttpSecurity 配置拦截策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf  前后端分离项目不需要防范跨站请求伪造
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()

                // 指定路径添加指定权限
                .antMatchers("/test").hasAuthority("system:dept:list")

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //自定义异常处理
        //授权失败处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //认证失败处理
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);


        //解决跨域问题
        http.cors();

    }



}
