package test;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.mapper.AdminMapper;
import com.atguigu.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//再类上标记必要的注解，Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml" , "classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource datasource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void test(){
        for (int i = 0; i < 238;i++){
            adminMapper.insert(new Admin(null , "jack" + i , "1231233" + i, "杰克" + i, "jack@qq.com" + i, null));
        }
    }

    @Test
    public void testTx(){
        Admin admin = new Admin(null,"jack","123455","杰克","jack@qq.com",null);
        adminService.saveAdmin(admin);
    }

    //测试能否正常连接数据库
    @Test
    public void testConnection() throws SQLException {
        Connection connection = datasource.getConnection();
        //sysout本质上是一个io操作，实际过程中影响性能，可以改为日志打印，并且通过日志级别批量控制打印的日志
        System.out.println(connection);
    }

    //测试往数据库中插入是否正常
    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin(null,"tom","123123","tom","tom@qq.com",null);
        int count = adminMapper.insert(admin);
        System.out.println("受影响的行数:" + count);
    }

    //测试日志功能
    @Test
    public void testLog(){
        //获取logger对象，传入要打印日志的类
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        //根据不同的日志级别打印日志
        logger.debug("Debug level!!!");

        logger.info("Info level!!!");

        logger.warn("Warn level!!!");

        logger.error("Error level!!!");
    }

}
