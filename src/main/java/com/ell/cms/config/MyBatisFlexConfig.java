package com.ell.cms.config;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.mybatisflex.core.audit.AuditManager;

@Configuration
public class MyBatisFlexConfig {
    private static final Logger logger = LoggerFactory
            .getLogger("mybatis-flex-sql");

    public MyBatisFlexConfig() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(
                auditMessage -> {
                    var sql = ansi().fg(YELLOW).a(auditMessage.getFullSql()).reset().toString();
                    var elapsedTime = auditMessage.getElapsedTime();
                    var elapsedTimeStr = elapsedTime + "ms";
                    var log = """

                            SQL: {} - 耗时: {}
                            """;
                    if (elapsedTime > 500) {
                        elapsedTimeStr = ansi().fg(RED).a(elapsedTimeStr).reset().toString();
                    } else {
                        elapsedTimeStr = ansi().fg(GREEN).a(elapsedTimeStr).reset().toString();
                    }
                    logger.info(log, sql, elapsedTimeStr);
                });
    }
}
