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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = DataSource2Config.BASE_PACKAGE,sqlSessionFactoryRef = "datasource2SqlSessionFactory")
public class DataSource2Config {

    private static final Logger log = LogManager.getLogger(DataSource2Config.class);

    static final String BASE_PACKAGE = "com.transcend.demo.dao.datasource2";
    private static final String MAPPER_LOCATION = "classpath:mapper/datasource2/*.xml";


    @Bean(name = "datasource2")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource datasource2() {
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
        log.info("datasource2 开始初始化。");
        return new DruidDataSource();
    }

    @Bean(name = "datasource2TransactionManager")
    public DataSourceTransactionManager datasource2Transaction(@Qualifier("datasource2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "datasource2SqlSessionFactory")
    public SqlSessionFactory datasource2SqlSessionFactory(@Qualifier("datasource2") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSource2Config.MAPPER_LOCATION));

        //添加PageHelper插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "oracle");
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

    @Bean(name = "datasource2SqlSessionTemplate")
    public SqlSessionTemplate datasource2SqlSessionTemplate(@Qualifier("datasource2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
