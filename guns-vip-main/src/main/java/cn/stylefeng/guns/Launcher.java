package cn.stylefeng.guns;

import cn.stylefeng.roses.core.config.MybatisDataSourceAutoConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {MybatisDataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class Launcher{

    private final static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
        logger.info(Launcher.class.getSimpleName() + " is success!");
    }
}
