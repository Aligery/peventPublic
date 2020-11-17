package ru.aid.pevent.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionResourceManager {

    private static final Logger log = LoggerFactory.getLogger(SqlSessionResourceManager.class);

    private static volatile SqlSessionResourceManager sqlMapperManager;

    private SqlSessionFactory sqlSessionFactory;


    public static SqlSessionResourceManager getInstance() {
        SqlSessionResourceManager localRepresentation = sqlMapperManager;
        if (localRepresentation==null) {
            synchronized (SqlSessionResourceManager.class) {
                localRepresentation = sqlMapperManager;
                if (localRepresentation==null) {
                    log.info("Instance MyBatis to SQL create");
                    localRepresentation = sqlMapperManager = new SqlSessionResourceManager();
                    log.info("Instance MyBatis succesfully created to SQL created");
                }
            }
        }
        return localRepresentation;
    }

    private SqlSessionResourceManager() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            log.error("Eror while creating based on resource manager", e);
        }
    }

    public SqlSession openNewSession() {
        return sqlSessionFactory.openSession();
    }

}
