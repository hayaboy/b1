package org.zerock.b1;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.DescriptorAccess;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
@Log4j2
public class DataSourceConnectTest {

    @Autowired
    DataSource dataSource;


    @Test
    void testConnect() throws SQLException {
       Connection connection = dataSource.getConnection();
    log.info("연결 객체 : " + connection);
    }
}
