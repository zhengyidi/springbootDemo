package com.transcend.demo.config.dataSourceConfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@MapperScan(basePackages = DataSource1Config.BASE_PACKAGE, sqlSessionFactoryRef = "datasource1SqlSessionFactory")
public class DataSource1Config {

    private static final Logger log = LogManager.getLogger(DataSource1Config.class);

    static final String BASE_PACKAGE = "com.transcend.demo.dao.datasource1";
    private static final String MAPPER_LOCATION = "classpath:mapper/datasource1/*.xml";


    @Primary
    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource datasource1() {
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
        log.info("datasource1 开始初始化。");
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "datasource1TransactionManager")
    public DataSourceTransactionManager datasource1Transaction(@Qualifier("datasource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "datasource1SqlSessionFactory")
    public SqlSessionFactory datasource1SqlSessionFactory(@Qualifier("datasource1") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSource1Config.MAPPER_LOCATION));

        //添加PageHelper插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");

        interceptor.setProperties(properties);
        factoryBean.setPlugins(new Interceptor[] {interceptor});

        return factoryBean.getObject();
    }

    @Primary
    @Bean(name = "datasource1SqlSessionTemplate")
    public SqlSessionTemplate datasource1SqlSessionTemplate(@Qualifier("datasource1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
