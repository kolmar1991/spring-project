package com.example;

import com.example.generated.tables.TestTable;
import com.example.generated.tables.records.TestTableRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class DemoApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DSLContext jooq;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        //jooq ready to use
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from test_table", Integer.class);
        Result<TestTableRecord> result = jooq.selectFrom(TestTable.TEST_TABLE).fetch();
        return "Hello World!: " + result.get(0).getTestfield();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}
