package cn.stylefeng.guns.config.datasource;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.sys.core.mybatis.sqlfilter.DemoProfileSqlInterceptor;
import cn.stylefeng.roses.core.metadata.CustomMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp的插件拓展和资源扫描
 *
 * @author fengshuonan
 * @Date 2019/5/10 21:33
 */
@Configuration
@MapperScan(basePackages = {"cn.stylefeng.**.mapper"})
public class PluginsConfig {

    /**
     * 拓展核心包中的字段包装器
     *
     * @author fengshuonan
     * @Date 2019/5/10 21:35
     */
    @Bean
    public CustomMetaObjectHandler gunsMpFieldHandler() {
        return new CustomMetaObjectHandler() {

            @Override
            protected Long getUserUniqueId() {
            try {
                return LoginContextHolder.getContext().getUser().getId();
            } catch (Exception e) {
                //如果获取不到当前用户就存空id
                return -100L;
            }
            }
        };
    }

    /**
     * 演示环境的sql拦截器
     * <p>
     * 演示环境只开放查询操作，其他都不允许
     *
     * @author stylefeng
     * @date 2020/5/5 12:24
     */
    @Bean
    public DemoProfileSqlInterceptor demoProfileSqlInterceptor() {
        return new DemoProfileSqlInterceptor();
    }
}